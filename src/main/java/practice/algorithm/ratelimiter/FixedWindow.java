package practice.algorithm.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindow implements RateLimiter {

  private static final int LIMIT = 5;
  private static final long WINDOW_SIZE = 60_000L; // 1min
  private static final ConcurrentHashMap<String, AtomicInteger> cnt = new ConcurrentHashMap<>();
  private static long refreshedAt = System.currentTimeMillis();

  @Override
  public boolean allowRequest(String id) {
    long now = System.currentTimeMillis();
    if (now - refreshedAt > WINDOW_SIZE) { // causes bursts at window edges
      cnt.clear();
      refreshedAt = now;
    }

    cnt.putIfAbsent(id, new AtomicInteger(0));
    if (cnt.get(id) == null) {
      return false;

    }

    return cnt.get(id).incrementAndGet() <= LIMIT;
  }
}
