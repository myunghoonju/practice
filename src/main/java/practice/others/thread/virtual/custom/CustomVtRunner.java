package practice.others.thread.virtual.custom;

import java.time.Duration;

public class CustomVtRunner {

  static void main() throws  InterruptedException {
    FileIoSample fileIoSample = new FileIoSample();
    for (int i = 0; i < 4; i++) {
      int finalId = i;
      CustomVirtualThread.start(() -> {
        System.out.println("Transfer: File_" + finalId + " Running in custom vt: " + CustomVirtualThread.current());

        fileIoSample.transfer("File_" + finalId);

        System.out.println("Transfer: File_" + finalId + " Completed custom vt: " + CustomVirtualThread.current());
      });
    }

    Thread.sleep(Duration.ofMinutes(1L));
  }
}
