package practice.others.thread.virtual;

public class SimpleThreadPoolDemo {

  static void main() throws InterruptedException {
    try(var pool = new SimpleThreadPool(4, 100)) {
      for (int i = 0; i < 100; i++) {
        int finalI = i;
        pool.submit(() -> runTask(finalI));
      }
    }

    Thread.sleep(10_000L);
    System.out.println("main finished");
  }

  private static void runTask(int finalI) {
    System.out.println("Task " + finalI + "on " + Thread.currentThread().getName());
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
