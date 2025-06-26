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
  private final ExpirableLockRegistry locker2;

  public DistributedLockAopService(ExpirableLockRegistry locker,
                                   ExpirableLockRegistry locker2) {
    this.locker = locker;
    this.locker2 = locker2;
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
        return null;
      }
  }

  public boolean tryLock(Lock l) {
    try {
      return l.tryLock(20_000L, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      log.error("tryLock failed.", e);
    }

    return false;
  }

  @Around("@annotation(practice.others.redis.DistributedSchedule)")
  public void distributedSchedule(ProceedingJoinPoint target) {
    Lock lockSchedule = locker2.obtain("locker2");;
    try {
      if (lockSchedule.tryLock(5_000L, TimeUnit.MILLISECONDS)) {
        try {
          target.proceed();
        } catch (Throwable e) {
          throw new RuntimeException("lockSchedule target exception", e);
        }
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }
}
