package practice.others.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.support.locks.ExpirableLockRegistry;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
@TestConfiguration
public class DistributedLockService {

  private final ExpirableLockRegistry locker;
  private final RedisTemplate<String, String> ops;

  public DistributedLockService(ExpirableLockRegistry locker,
                                RedisTemplate<String, String> template) {
    this.locker = locker;
    this.ops = template;
  }

  public int result() {
    return List.of(ops.opsForValue().get("test1").split("")).size();
  }

  public void clean() {
    ops.opsForValue().getAndDelete("test1");
  }

  public void test(String val) {
    try {
      Lock locker1 = locker.obtain("locker");
      if (locker1.tryLock(10_000L, TimeUnit.MILLISECONDS)) {
        try {
          ops.opsForValue().setIfAbsent("test1", val);
          ops.opsForValue().append("test1", val);
          Thread.sleep(1_000L);
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
      ops.opsForValue().setIfAbsent("test1", val);
      ops.opsForValue().append("test1", val);
      Thread.sleep(1_000L);
      log.error("set success.");
    } catch (Exception e) {
      log.error("set failed.", e);
    }
  }
}
