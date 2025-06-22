package practice.others.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.integration.support.locks.ExpirableLockRegistry;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
@TestConfiguration
public class DistributedLockService {

  private final ExpirableLockRegistry locker;
  private final ValueOperations<String, String> ops;

  public DistributedLockService(ExpirableLockRegistry locker,
                                ValueOperations<String, String> ops) {
    this.locker = locker;
    this.ops = ops;
  }

  public int result() {
    return ops.get("test") != null ? ops.get("test").split("").length : 0;
  }

  public void clean() {
    ops.getAndDelete("test");
  }

  public void test(String val) {
    try {
      Lock locker1 = locker.obtain("locker");
      if (locker1.tryLock(10_000L, TimeUnit.MILLISECONDS)) {
        try {
          ops.set("test", ops.get("test") == null ? val : ops.get("test") + val);
          Thread.sleep(2_000L);
          log.error("set success.");
        } finally {
          locker1.unlock();
        }
      } else {
        log.error("tryLock failed.");
      }
    } catch (Exception e) {
      log.error("test failed.", e);
    }
  }

  public void testWithoutLock(String val) {
    try {
      ops.set("test", ops.get("test") == null ? val : ops.get("test") + val);
      log.error("set success.");
    } catch (Exception e) {
      log.error("set failed.", e);
    }
  }
}
