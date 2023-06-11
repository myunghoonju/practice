package practice.others.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import practice.others.cache.model.UserCache;
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
    private Cache<String, UserCache> user;
    private Cache<String, Agency> agencyCache;

    private final AgencyInfoRepository agencyInfoRepository;

    public UserCacheWrapper(CacheManager userCacheManager, AgencyInfoRepository agencyInfoRepository) {
        this.userCacheManager = userCacheManager;
        this.agencyInfoRepository = agencyInfoRepository;
        this.user = userCacheManager.getCache("user");
        this.agencyCache = userCacheManager.getCache("agencyCache");
    }

    public Object getUser(String key) {
        return this.user.get(key);
    }

    public Agency getAgency(String key) {
        return agencyCache.get(key);
    }

    public void putUserCache(String key, UserCache content) {
        user.put(key, content);
    }
}
