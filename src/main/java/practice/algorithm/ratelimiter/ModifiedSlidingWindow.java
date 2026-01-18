package practice.algorithm.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ModifiedSlidingWindow implements RateLimiter {

  private final long windowSize;
  private final ConcurrentHashMap<Long, AtomicInteger> cnt = new ConcurrentHashMap<>();

  public ModifiedSlidingWindow(long windowSize) {
    this.windowSize = windowSize;
  }

  @Override
  public boolean allowRequest(String id) {
    long currentWindow = System.currentTimeMillis() / windowSize;

    AtomicInteger current = cnt.computeIfAbsent(currentWindow, k -> new AtomicInteger(0));

    AtomicInteger prev = cnt.get(currentWindow - 1);
    int prevCnt = (prev == null ? 0 : prev.get()) / 2;

    for (int i = 0; i < 5; i++) {
      int currCnt = current.get();
      int load = currCnt + prevCnt;
      if (load >= 5) {
        return false;
      }

      if (current.compareAndSet(currCnt, currCnt + 1)) {
        return true;
      }
    }

    return false;
  }
}
