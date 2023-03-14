package practice.others.cache.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import practice.others.cache.UserCacheWrapper;

@Slf4j
@Service
public class Consumer {

    private final UserCacheWrapper userCacheWrapper;

    public Consumer(UserCacheWrapper userCacheWrapper) {
        this.userCacheWrapper = userCacheWrapper;
    }

    @RabbitListener(queues = "q.blocking-queue", containerFactory = "retryContainerFactory")
    public void consumeBlocking(String payload) throws Exception {
        log.info("Processing message from blocking-queue: {}", payload);
        userCacheWrapper.setAgencyCache();
        throw new Exception("exception occured!");
    }
}
