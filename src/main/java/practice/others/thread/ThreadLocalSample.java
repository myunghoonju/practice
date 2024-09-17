package practice.others.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalSample {

  private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "-default-");

  public static void main(String[] args) {
    new Thread(() -> {
      log.info("name & value of thread one -> {}", Thread.currentThread().getName() + threadLocal.get());
      threadLocal.set("value~~~");
      log.info("name & value of thread one -> {}", Thread.currentThread().getName() + threadLocal.get());
      threadLocal.remove();
      log.info("after removing name & value of thread one -> {}", Thread.currentThread().getName() + threadLocal.get());
    }, "threadOne").start();

    new Thread(() -> {
      log.info("name & value of thread two -> {}", Thread.currentThread().getName() + threadLocal.get());
      threadLocal.set("value~~~~~");
      log.info("name & value of thread two -> {}", Thread.currentThread().getName() + threadLocal.get());
    }, "threadTwo").start();
  }
}
