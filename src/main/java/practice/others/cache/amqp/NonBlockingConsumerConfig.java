package practice.others.cache.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import practice.others.cache.UserCacheWrapper;

@Slf4j
@Configuration
public class NonBlockingConsumerConfig {

    private final UserCacheWrapper userCacheWrapper;

    public NonBlockingConsumerConfig(UserCacheWrapper userCacheWrapper) {
        this.userCacheWrapper = userCacheWrapper;
    }
}
