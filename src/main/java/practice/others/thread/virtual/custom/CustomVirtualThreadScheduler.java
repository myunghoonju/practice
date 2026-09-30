package practice.others.thread.virtual.custom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class CustomVirtualThreadScheduler {

  public static final ThreadLocal<CustomVirtualThread> CURRENT_VT = new ThreadLocal<>();
  public static final ScheduledExecutorService IO_EVENT_SCHEDULER = Executors.newSingleThreadScheduledExecutor(Thread.ofPlatform().daemon().factory());
  private final ExecutorService workStealingPool = Executors.newWorkStealingPool(2);

  public void schedule(CustomVirtualThread thread) {
    workStealingPool.submit(() -> {
      CURRENT_VT.set(thread);
      thread.run();
      CURRENT_VT.remove();
    });
  }
}
