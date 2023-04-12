package practice.others.cache;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.event.EventType;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import practice.others.multipleDb.domain.Agency;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;

@Component
public class CacheConfig {

    @Bean
    public CacheManager userCacheManager() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        ResourcePools pool = ResourcePoolsBuilder.heap(1000).build();

        CacheEventListenerConfigurationBuilder listenerConfig = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(new CacheListener(), EventType.CREATED, EventType.UPDATED)
                .unordered().asynchronous();

        CacheConfiguration<String, String> config = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, pool)
                                                                             .withService(listenerConfig)
                                                                             .withExpiry(ExpiryPolicy.NO_EXPIRY)
                                                                             .build();

        CacheConfiguration<String, Agency> agencyConfig = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Agency.class, pool)
                                                                             .withService(listenerConfig)
                                                                             .build();

        Configuration<String, String> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(config);
        Configuration<String, Agency> agencyconfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(agencyConfig);

        cacheManager.createCache("userA", configuration);
        cacheManager.createCache("userB", configuration);
        cacheManager.createCache("agencyCache", agencyconfiguration);

        return cacheManager;
    }
}
