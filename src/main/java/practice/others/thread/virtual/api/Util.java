package practice.others.thread.virtual.api;

public class Util {

  public static void threadSleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {}
  }
}
