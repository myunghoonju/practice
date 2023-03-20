package practice.others.cache.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import practice.others.cache.UserCacheWrapper;
import practice.others.secret.okhttp.MybootApiService;
import practice.others.secret.okhttp.RetrofitService;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer implements DisposableBean {

    private final UserCacheWrapper userCacheWrapper;
    private final AmqpAdmin rabbitAmqpAdmin;
    private final Queue blockingQueue;
    private final RabbitTemplate rabbitTemplate;

    private static String test = "";

    @Override
    public void destroy() {
        log.warn("delete queue:: {}", blockingQueue.getName());
        rabbitAmqpAdmin.deleteQueue(blockingQueue.getName());
    }

    @RabbitListener(queues = "#{blockingQueue.getName()}",
                    containerFactory = "retryContainerFactory",
                    autoStartup = "true")
    public void consumeBlocking(String payload) throws Exception {
        log.info("Processing message from blocking-queue: {}", payload);
        test = payload;
        throw new Exception("exception occured!");
    }

    public static void initialPush() throws Exception {
        MybootApiService mybootApiService = RetrofitService.getCli("http://localhost:8085/")
                .create(MybootApiService.class);

        mybootApiService.push().execute();
        log.info("my-boot push");
    }

    //@Scheduled(cron = "0 */1 * * * *")
    public void receive() {
        if (!StringUtils.hasText(test)) {
            String receive = (String) rabbitTemplate.receiveAndConvert(blockingQueue.getName());
            log.info("received from method:: {}", receive);
        }
    }
}

