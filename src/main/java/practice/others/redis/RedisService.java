package practice.others.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@EnableScheduling
public class RedisService {

  @Autowired
  Environment environment;

  private final DistributedLockAopService  distributedLockAopService;
  private final RedisTemplate<String, String> template;


  public RedisService(DistributedLockAopService distributedLockAopService,
                      RedisTemplate<String, String> template) {
    this.distributedLockAopService = distributedLockAopService;
    this.template = template;
  }

  @DistributedSchedule
  @Scheduled(fixedDelay = 10_000L)
  public void task() {
    log.info("DistributedSchedule task start");
  }

  @RedisTransactional
  public List<String> list(String val) {
    String port = environment.getProperty("local.server.port");
    try {
      if (!template.opsForValue().setIfAbsent("test", val)) {
        template.opsForValue().append("test", " x " + port);
      }
    } catch (Exception e) {
      log.error("set failed.", e);
    }

    return List.of(template.opsForValue().get("test"));
  }
}