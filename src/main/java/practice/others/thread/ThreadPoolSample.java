package practice.others.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolSample {

  private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();
  private static final InheritableThreadLocal<String> INHERITABLE = new InheritableThreadLocal<>();

  public static void main(String[] args) throws InterruptedException {
    threadLocalMapTest();
    inheritableThreadLocalTest();
  }

  private static void threadLocalMapTest() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    executorService.submit(() -> {
      THREAD_LOCAL.set("thread-local");
      System.out.println(Thread.currentThread().getName() + "  " + THREAD_LOCAL.get());
      THREAD_LOCAL.remove();
    });

    Thread.sleep(3000L);

    for (int i = 0; i < 5; i++) {
      executorService.submit(() -> System.out.println(Thread.currentThread().getName() + "  " + THREAD_LOCAL.get()));
    }

    executorService.shutdown();
  }

  private static void inheritableThreadLocalTest() throws InterruptedException {
    INHERITABLE.set("부모");
    Thread child = new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + " 상속받은 값 " + INHERITABLE.get());
      INHERITABLE.set("상속");
      System.out.println(Thread.currentThread().getName() + " 자식설정 값 " + INHERITABLE.get());
    });

    child.start();
    child.join();

    System.out.println(Thread.currentThread().getName() + " 부모 값 " + INHERITABLE.get());
  }
}
