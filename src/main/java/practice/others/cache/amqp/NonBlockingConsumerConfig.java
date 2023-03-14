package practice.others.cache.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.others.cache.UserCacheWrapper;

@Slf4j
@Configuration
public class NonBlockingConsumerConfig {

    private final UserCacheWrapper userCacheWrapper;

    public NonBlockingConsumerConfig(UserCacheWrapper userCacheWrapper) {
        this.userCacheWrapper = userCacheWrapper;
    }

    @Bean
    public Queue retryWaitEndedQueue() {
        return QueueBuilder.nonDurable("retry-wait-ended-queue").build();
    }
}
