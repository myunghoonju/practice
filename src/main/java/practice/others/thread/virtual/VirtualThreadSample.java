package practice.others.thread.virtual;

import java.util.List;
import java.util.stream.IntStream;

public class VirtualThreadSample {

  static void main() throws InterruptedException {
    VirtualThreadSample sample = new VirtualThreadSample();
    sample.threadPinningFixed();
  }

  void threadPinningFixed() throws InterruptedException {
    List<Thread> list = IntStream.range(0, 100).mapToObj(i -> Thread.ofVirtual().unstarted(() -> {
      synchronized (new Object()) {
        try {
          Thread.sleep(1_000);
          System.out.println(Thread.currentThread());
        } catch (InterruptedException _) {}
      }
    })).toList();

    list.forEach(Thread::start);

    for (Thread thread : list) {
      thread.join();
    }
  }
}