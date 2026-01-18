package practice.algorithm.ratelimiter;

import java.util.LinkedList;

public class LeakyBucket implements RateLimiter {

  private final int capacity;
  private final long leakRate;
  private final LinkedList<Long> queue;

  public LeakyBucket(int capacity, long leakRate) {
    this.capacity = capacity;
    this.leakRate = leakRate;
    this.queue = new LinkedList<>();
  }

  @Override
  public synchronized boolean allowRequest(String id) {
    long current = System.currentTimeMillis();
    while (!queue.isEmpty() && (current - queue.peek() > leakRate)) {
      queue.poll();
    }

    if (queue.size() < capacity) {
      queue.add(current);
      return true;
    }

    return false;
  }
}
