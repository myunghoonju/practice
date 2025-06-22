package practice.others.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@EnableScheduling
public class RedisService {

  private final RedisTemplate<String, String> template;

  public RedisService(RedisTemplate<String, String> template) {
    this.template = template;
  }

  @RedisTransactional
  public List<String> list(String val) {
    try {
      template.opsForValue().set("test", template.opsForValue().get("test") == null ?
              val : template.opsForValue().get("test") + val);
    } catch (Exception e) {
      log.error("set failed.", e);
    }

    return List.of(template.opsForValue().get("test").split(""));
  }
}
