package practice.others.thread.virtual;

import java.time.Duration;
import java.util.stream.IntStream;

//-Djdk.traceVirtualThreadLocals=true
public class VirtualThreadUsesThreadLocal {

  static void main() {
    ThreadLocal<LargeObject> threadLocal = ThreadLocal.withInitial(LargeObject::new);

    var threadList = IntStream.range(0, 1000)
        .mapToObj(i -> Thread.ofVirtual().unstarted(() -> {
          LargeObject largeObject = threadLocal.get();
          useIt(largeObject);
          sleep();
        })).toList();

    threadList.forEach(Thread::start);
    threadList.forEach(thread -> {
      try {
        thread.join();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });
  }

  private static void useIt(LargeObject largeObject) {
    System.out.print(largeObject.data.length);
  }

  private static void sleep() {
    try {
      Thread.sleep(Duration.ofSeconds(5));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  static class LargeObject {
    private byte[] data = new byte[1024 * 500]; // 500 KB
  }
}
