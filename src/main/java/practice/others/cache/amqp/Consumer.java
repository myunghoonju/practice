package practice.others.cache.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import practice.others.secret.okhttp.MybootApiService;
import practice.others.secret.okhttp.RetrofitService;

import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer {

    private final Queue blockingQueue;
    private final AmqpAdmin rabbitAmqpAdmin;

    @PreDestroy
    public void destroy() {
        log.warn("delete queue:: {}", blockingQueue.getName());
        rabbitAmqpAdmin.deleteQueue(blockingQueue.getName());
    }

    @RabbitListener(queues = "#{blockingQueue.getName()}",
                    containerFactory = "retryContainerFactory")
    public void consumeBlocking(List<PayLoad> payload) {
        payload.forEach(payLoad -> {
            log.info("name: {}", payLoad.getName());
            payLoad.getList().forEach(li -> log.info("list {}", li));
        });
    }

    public static void initialPush() throws Exception {
        MybootApiService mybootApiService = RetrofitService.getCli("http://localhost:8085/")
                                                           .create(MybootApiService.class);
        mybootApiService.push().execute();
        log.info("my-boot push");
    }

    public void queueInfo() {
        QueueInformation queueInfo = rabbitAmqpAdmin.getQueueInfo(blockingQueue.getName());
        if (queueInfo != null) {
            log.info(queueInfo.toString());
        }
    }
}

