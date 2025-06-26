package practice.others.redis;

import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.support.locks.ExpirableLockRegistry;

import java.time.Duration;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Object> template() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new StringRedisSerializer());

    return template;
  }

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
    RedisNode redisNode = new RedisNode("localhost", 6381);
    redisClusterConfiguration.addClusterNode(redisNode);
    return new LettuceConnectionFactory(redisClusterConfiguration ,getLettuceClientConfiguration());
  }

  @Bean
  public DistributedLockAopService distributedLockAopService() {
    return new DistributedLockAopService(locker(),  locker2());
  }

  @Bean
  public ExpirableLockRegistry locker() {
    return new RedisLockRegistry(redisConnectionFactory(), "locker", 30_000L);
  }

  @Bean
  public ExpirableLockRegistry locker2() {
    return new RedisLockRegistry(redisConnectionFactory(), "locker2", 10_000L);
  }

  private static LettuceClientConfiguration getLettuceClientConfiguration() {
    return LettuceClientConfiguration.builder()
            .commandTimeout(Duration.ofMillis(1000))// timeout rule
            .shutdownTimeout(Duration.ofMillis(1000))  // shutting down time limit
            .clientOptions(ClusterClientOptions.builder()
                    .topologyRefreshOptions(ClusterTopologyRefreshOptions.builder()
                            .enablePeriodicRefresh(Duration.ofMillis(10000)) // refresh node info duration
                            .enableAllAdaptiveRefreshTriggers()
                            .adaptiveRefreshTriggersTimeout(Duration.ofMillis(3000))
                            .build())
                    .timeoutOptions(TimeoutOptions.enabled())
                    .build())
            .build();
  }
}
