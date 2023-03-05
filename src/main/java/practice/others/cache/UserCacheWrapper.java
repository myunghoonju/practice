package practice.others.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import practice.others.cache.domain.AgencyInfo;
import practice.others.cache.domain.AgencyInfoRepository;
import practice.others.cache.domain.model.Agency;

import javax.annotation.PostConstruct;
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

    public void setAgencyCache() {
        try {
            AgencyInfo agencyInfo = agencyInfoRepository.findByAgencyCd("TEST");
            Agency agency = new Agency();
            agency.test(agencyInfo);
            agencyCache.put(agencyInfo.getAgencyCd(), agency);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   /* @PostConstruct
    void init() {
        String agency = "TEST";
        String info = "{ \"url\": { \"base\": \"https://api.happycrew.co.kr\", \"deposit\": \"/pos/foodTech/search/point\", \"fee\": \"/pos/foodTech/search/distance\", \"request\": \"/pos/foodTech/delivery/insert\", \"update\": \"/pos/foodTech/delivery/modify\", \"cancel\": \"/pos/foodTech/delivery/cancel\" }, \"header\": { \"X-CLIENT-TOKEN\": \"f5653057f739b4b8fa606f4dc0d326d5a24345f3a866cb5778511807f6b7ac9c\", \"Content-Type\": \"application/json; charset=UTF-8\" } }";
        AgencyInfo agencyInfo = AgencyInfo.builder()
                                          .agencyCd(agency)
                                          .information(info)
                                          .build();
        agencyInfoRepository.save(agencyInfo);
    }*/
}
