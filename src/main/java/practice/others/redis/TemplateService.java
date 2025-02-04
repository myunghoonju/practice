package practice.others.redis;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TemplateService {

//  private final ValueOperations<String, String> ops;
//
//  public TemplateService(ValueOperations<String, String> ops) {
//    this.ops = ops;
//  }
//
//  public boolean test(String key, String value) {
//    return Boolean.TRUE.equals(ops.setIfAbsent(key, value, 10L, TimeUnit.SECONDS));
//  }
}
