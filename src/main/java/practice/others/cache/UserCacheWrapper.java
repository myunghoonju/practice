package practice.others.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import javax.cache.CacheManager;



@Slf4j
@Service
public class UserCacheWrapper {

    private final CacheManager userCacheManager;
    private Cache<String, String> userA;
    private Cache<String, String> userB;

    public UserCacheWrapper(CacheManager userCacheManager) {
        this.userCacheManager = userCacheManager;
        this.userA = userCacheManager.getCache("userA");
        this.userB = userCacheManager.getCache("userB");
    }

    public Object getUser(String key) {
        Object o1 = this.userA.get(key);
        Object o2 = this.userB.get(key);
        log.info("a key {} val {}", key, o1.toString());
        log.info("b key {} val {}", key, o2.toString());
        return key;
    }

    public void putUserToLocalCache(String key, String value) {
        userA.put(key, value);
        userB.put(key, value);
    }
}
