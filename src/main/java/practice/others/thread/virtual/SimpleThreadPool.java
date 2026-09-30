package practice.others.thread.virtual;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class SimpleThreadPool implements AutoCloseable {

  private final BlockingQueue<Runnable> queue;
  private final ThreadGroup threadGroup;
  private volatile boolean running = true;

  public SimpleThreadPool(int poolSize, int queueSize) {
    Worker[] threads = new Worker[poolSize];
    queue = new LinkedBlockingDeque<>(queueSize);
    threadGroup = new ThreadGroup("SimpleThreadPool");

    for (int idx = 0; idx < poolSize; idx++) {
      threads[idx] = new Worker(threadGroup, "worker #" + idx);
      threads[idx].start();
    }
  }

  public void submit(Runnable task) {
    try {
      queue.put(task);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void shutdown() {
    this.running = false;
    threadGroup.interrupt();
  }

  @Override
  public void close() {
    while (!queue.isEmpty()) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }
    }

    shutdown();
  }

  class Worker extends Thread {
    public Worker(ThreadGroup group, String name) {
      super(group, name);
    }

    @Override
    public void run() {
      while (running) {
        try {
          Runnable task = queue.take();
          task.run();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}
