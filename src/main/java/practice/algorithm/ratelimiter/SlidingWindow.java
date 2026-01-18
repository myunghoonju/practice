package practice.algorithm.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindow implements RateLimiter {

  private final int limit;
  private final long windowSize;
  private final ConcurrentHashMap<Long, AtomicInteger> cnt = new ConcurrentHashMap<>();

  public SlidingWindow(int limit, long windowSize) {
    this.limit = limit;
    this.windowSize = windowSize;
  }

  @Override
  public boolean allowRequest(String id) {
    long currentWindow = System.currentTimeMillis() / windowSize;
    cnt.putIfAbsent(currentWindow, new AtomicInteger(0));
    cnt.putIfAbsent(currentWindow - 1, new AtomicInteger(0));

    int currentCnt = cnt.get(currentWindow).get();
    int prevCnt = cnt.get(currentWindow - 1).get();

    // prevent bursts
    return (currentCnt + prevCnt / 2) < limit &&
           cnt.get(currentWindow).incrementAndGet() <= limit;
  }
}
