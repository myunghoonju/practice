package practice.others.thread.virtual;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class MonitorVirtualThread {

  private static final int THREAD_COUNT = 1_000;
  private static final Duration WORK_DURATION = Duration.ofSeconds(5);
  private static final Duration DELAY_BEFORE_DUMP = Duration.ofSeconds(2);

  static void main() {
    long pid = ProcessHandle.current().pid();
    String outputFile = "/Users/ju/Projects/practice/dump.json";
    deleteIfExists(outputFile);

    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      IntStream.range(0, THREAD_COUNT).forEach(i -> executor.submit(() -> sleep(WORK_DURATION)));
      executor.submit(() -> {
        sleep(DELAY_BEFORE_DUMP);
        runJcmdDump(pid, outputFile);
      });
    }
  }

  private static void deleteIfExists(String file) {
    try {
      Files.deleteIfExists(Path.of(file));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void sleep(Duration d) {
    try {
      TimeUnit.NANOSECONDS.sleep(d.toNanos());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void runJcmdDump(long pid, String file) {
    if (!new File(file).isAbsolute()) {
      throw new IllegalArgumentException("Output path must be absolute.");
    }
    ProcessBuilder pb = new ProcessBuilder(List.of("/bin/sh", "-c", String.format("jcmd %d Thread.dump_to_file -format=json %s", pid, file)));
    try {
      Process p = pb.start();
      int exit = p.waitFor();
      if (exit != 0) {
        System.err.printf("jcmd exited %d%n", exit);
        p.getInputStream().transferTo(System.err);
        p.getErrorStream().transferTo(System.err);
      }
    } catch (IOException | InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Failed to run jcmd: " + e.getMessage());
    }
  }
}
