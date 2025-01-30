package practice.others;

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

import java.time.Duration;

@Configuration
public class RedisConfig {

  @Bean
  public ValueOperations<Object, Object> ops() {
    return template().opsForValue();
  }

  @Bean
  public RedisTemplate<Object, Object> template() {
    RedisTemplate<Object, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory());
    template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
    template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

    return template;
  }

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
    RedisNode redisNode = new RedisNode("localhost", 6381);
    redisClusterConfiguration.addClusterNode(redisNode);
    return new LettuceConnectionFactory(redisClusterConfiguration ,getLettuceClientConfiguration());
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
