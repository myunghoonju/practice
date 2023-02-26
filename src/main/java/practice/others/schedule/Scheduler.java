package practice.others.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import practice.others.cache.UserCacheWrapper;

@Slf4j
@Service
public class Scheduler {

    private final UserCacheWrapper userCacheWrapper;

    public Scheduler(UserCacheWrapper userCacheWrapper) {
        this.userCacheWrapper = userCacheWrapper;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void mytask() {
        userCacheWrapper.setAgencyCache();
    }
}
