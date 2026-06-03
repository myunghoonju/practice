package practice.others.road_to_ga.restclient.registry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

// Spring 7 방식: @ImportHttpServices 선언만으로 프레임워크가 PostHttpClient 빈을 자동 생성
// PostHttpClient를 직접 @Autowired/@Bean 없이 주입 가능
@Configuration
@EnableResilientMethods
@ImportHttpServices(group = "posts", types = PostHttpClient.class)
class RegistryConfig {

    @Bean
    RestClientHttpServiceGroupConfigurer registryPostsGroupConfigurer(
            @Value("${posts.base-url:https://jsonplaceholder.typicode.com}") String baseUrl) {
        return groups -> groups
                .filterByName("posts")
                .forEachClient((_, builder) -> builder.baseUrl(baseUrl));
    }
}
