package practice.others.redis;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TemplateService {

  private final ValueOperations<Object, Object> ops;

  public TemplateService(ValueOperations<Object, Object> ops) {
    this.ops = ops;
  }

  public boolean test(String key, String value) {
    return Boolean.TRUE.equals(ops.setIfAbsent(key, value, 1L, TimeUnit.SECONDS));
  }
}
