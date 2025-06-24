package practice.others.redis;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.integration.support.locks.ExpirableLockRegistry;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
@Aspect
public class DistributedLockAopService {

  private final ExpirableLockRegistry locker;

  public DistributedLockAopService(ExpirableLockRegistry locker) {
    this.locker = locker;
  }

  @Around("@annotation(practice.others.redis.RedisTransactional)")
  public Object redisTransactional(ProceedingJoinPoint target) {
    Lock locker1 = locker.obtain("locker");
    if (tryLock(locker1)) {
        try {
          return target.proceed();
        } catch (Throwable e) {
          throw new RuntimeException("target exception", e);
        } finally {
          locker1.unlock();
        }
      } else {
        log.error("tryLock failed. It'll be expired");
      }

      throw new RuntimeException("redisTransactional exception");
  }

  private boolean tryLock(Lock locker1) {
    try {
      return locker1.tryLock(20_000L, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      log.error("tryLock failed.", e);
    }

    return false;
  }
}
