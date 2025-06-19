package practice.others.redis;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.integration.support.locks.ExpirableLockRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Service
public class TemplateService {

  private final ExpirableLockRegistry locker;
  private final ValueOperations<String, String> ops;

  public TemplateService(ExpirableLockRegistry locker,
                         ValueOperations<String, String> ops) {
    this.locker = locker;
    this.ops = ops;
  }

  public boolean test(String key, String value) {
    Lock locker1 = locker.obtain("locker");
    if (locker1.tryLock()){
      return Boolean.TRUE.equals(ops.setIfAbsent(key, value, 10L, TimeUnit.SECONDS));
    }

    locker1.unlock();
    return false;
  }
}
