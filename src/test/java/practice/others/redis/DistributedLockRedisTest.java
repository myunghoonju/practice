package practice.others.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.stream.Stream;

@SpringBootTest
@Import(DistributedLockService.class)
public class DistributedLockRedisTest {

  @Autowired
  DistributedLockService service;

  @Test
  public void concurrent_caching_test() {
    for (int i = 0; i < 10; i++) {
      Stream.of("1","2","4","5")
              .parallel()
              .forEach(it -> service.test(it));
    }
  }
}
