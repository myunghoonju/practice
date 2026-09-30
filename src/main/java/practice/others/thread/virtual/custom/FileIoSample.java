package practice.others.thread.virtual.custom;

import jdk.internal.vm.Continuation;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FileIoSample {

  private final Random random = new Random();

  public void transfer(String path) {
    System.out.println("Start transfer: " + path);
    CustomVirtualThread current = CustomVirtualThread.current();
    CustomVirtualThreadScheduler.IO_EVENT_SCHEDULER.schedule(() -> CustomVirtualThread.SCHEDULER.schedule(current),
                                                                   random.nextInt(1000),
                                                                   TimeUnit.MILLISECONDS);
    CustomVirtualThreadScheduler.CURRENT_VT.remove();
    Continuation.yield(CustomVirtualThread.SCOPE);
    System.out.println("Transfer file completed: " + path);
  }
}
