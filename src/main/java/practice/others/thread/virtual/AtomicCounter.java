package practice.others.thread.virtual;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class AtomicCounter {

  private volatile int counter = 0;
  private static final VarHandle COUNTER_HANDLE;

  static {
    try {
      COUNTER_HANDLE = MethodHandles.lookup().findVarHandle(
          AtomicCounter.class, "counter", int.class);
    } catch (ReflectiveOperationException e) {
      throw new Error(e);
    }
  }

  public void increment() {
    int current;
    int next;

    do {
      current = counter;
      next = current + 1;
    } while (!COUNTER_HANDLE.compareAndSet(this, current, next));
  }

  public int get() {
    return counter;
  }

  static void main() throws Exception {
    AtomicCounter cnt = new AtomicCounter();

    Thread t1 = Thread.ofPlatform().start(() -> {
      for (int i = 0; i < 100; i++) {
        cnt.increment();
      }
    });

    Thread t2 = Thread.ofPlatform().start(() -> {
      for (int i = 0; i < 100; i++) {
        cnt.increment();
      }
    });

    t1.join();
    t2.join();

  }
}
