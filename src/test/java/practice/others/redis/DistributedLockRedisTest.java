package practice.others.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(DistributedLockService.class)
public class DistributedLockRedisTest {

  @Autowired
  DistributedLockService service;

  @Test
  public void concurrent_caching_test() {
    for (int i = 0; i < 4; i++) {
      Stream.of("1","2","3","4","5")
              .parallel()
              .forEach(it -> service.test(it));
    }

    assertEquals(20, service.result());
    service.clean();
  }

  @Test
  public void concurrent_caching_without_lock_test() {
    for (int i = 0; i < 1; i++) {
      Stream.of("1","2","3","4","5")
              .parallel()
              .forEach(it -> service.testWithoutLock(it));
    }

    assertEquals(1, service.result());
    service.clean();
  }
}
