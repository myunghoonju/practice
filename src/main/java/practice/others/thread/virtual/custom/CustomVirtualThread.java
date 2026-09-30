package practice.others.thread.virtual.custom;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomVirtualThread {

  public static final CustomVirtualThreadScheduler SCHEDULER = new CustomVirtualThreadScheduler();
  private static final AtomicInteger COUNTER = new AtomicInteger(1);
  public static final ContinuationScope SCOPE = new ContinuationScope("customVirtualThread");

  private final int id;
  private final Continuation continuation;

  public CustomVirtualThread(Runnable runnable) {
    this.id = COUNTER.getAndIncrement();
    this.continuation = new Continuation(SCOPE, runnable);
  }

  public void run() {
    continuation.run();
  }

  public static void start(Runnable runnable) {
    CustomVirtualThread thread = new CustomVirtualThread(runnable);
    SCHEDULER.schedule(thread);
  }

  public static CustomVirtualThread current() {
    return SCHEDULER.CURRENT_VT.get();
  }

  @Override
  public String toString() {
    return "Custom virtual thread " + id + "-" + Thread.currentThread().getName();
  }
}
