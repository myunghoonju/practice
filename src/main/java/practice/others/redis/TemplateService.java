package practice.others.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.integration.support.locks.ExpirableLockRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
@Service
@EnableScheduling
public class TemplateService {

  private final ExpirableLockRegistry locker;
  private final ValueOperations<String, String> ops;

  public TemplateService(ExpirableLockRegistry locker,
                         ValueOperations<String, String> ops) {
    this.locker = locker;
    this.ops = ops;
  }

  @Scheduled(fixedDelay = 3000L)
  public void test() {
    try {
      Lock locker1 = locker.obtain("locker");
      if (locker1.tryLock(3000, TimeUnit.MILLISECONDS)) {
        try {
          ops.set("test", ops.get("test") + UUID.randomUUID().toString().substring(0, 1));
          Thread.sleep(5000);
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
}
