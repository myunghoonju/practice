package practice.others.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import practice.others.multipleDb.domain.Agency;
import practice.others.multipleDb.domain.info.AgencyInfoRepository;

import javax.cache.Cache;
import javax.cache.CacheManager;

/**
 *  test only
 * */
@Slf4j
@Service
public class UserCacheWrapper {

    private final CacheManager userCacheManager;
    private Cache<String, String> userA;
    private Cache<String, String> userB;
    private Cache<String, Agency> agencyCache;

    private final AgencyInfoRepository agencyInfoRepository;

    public UserCacheWrapper(CacheManager userCacheManager, AgencyInfoRepository agencyInfoRepository) {
        this.userCacheManager = userCacheManager;
        this.agencyInfoRepository = agencyInfoRepository;
        this.userA = userCacheManager.getCache("userA");
        this.userB = userCacheManager.getCache("userB");
        this.agencyCache = userCacheManager.getCache("agencyCache");
    }

    public Object getUserA(String key) {
        return this.userA.get(key);
    }

    public Agency getAgency(String key) {
        return agencyCache.get(key);
    }

    public void putUserToLocalCache(String key, String value) {
        userA.put(key, value);
        userB.put(key, value);
    }
}
