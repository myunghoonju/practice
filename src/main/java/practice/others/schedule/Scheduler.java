package practice.others.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class Scheduler {

    @Scheduled(cron = "0 */1 * * * *")
    public void mytask() {
        log.info("time --> {}", LocalDateTime.now());
    }
}
