package practice.algorithm.ratelimiter;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket implements RateLimiter {

  private final int capacity;
  private final int refillRate;
  private final AtomicInteger tokens;
  private long recentRefillAt;

  public TokenBucket(int capacity, int refillRate) {
    this.capacity = capacity;
    this.refillRate = refillRate;
    this.tokens = new AtomicInteger(capacity);
    this.recentRefillAt = System.currentTimeMillis();
  }

  @Override
  public boolean allowRequest(String id) {
    refill();
    if (tokens.get() > 0) {
      tokens.decrementAndGet();
      return true;
    }

    return false;
  }

  private void refill() {
    long now = System.currentTimeMillis();
    int newTokens = (int) ((now - recentRefillAt) / refillRate * 1_000);
    if (newTokens > 0) {
      tokens.set(Math.min(capacity, tokens.get() + newTokens));
      recentRefillAt = now;
    }
  }
}
