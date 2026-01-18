package practice.algorithm.ratelimiter;

public interface RateLimiter {

  boolean allowRequest(String id);
}
