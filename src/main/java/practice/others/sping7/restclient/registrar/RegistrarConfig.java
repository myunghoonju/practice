package practice.others.sping7.restclient.registrar;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;

// Spring 7 프로그래밍형 방식: AbstractHttpServiceRegistrar를 직접 구현
// @ImportHttpServices(declarative)와 달리 조건부 등록, 동적 패키지 스캔 등 세밀한 제어 가능
@Configuration
@EnableResilientMethods
@Import(PostHttpServiceRegistrar.class)
class RegistrarConfig {

    @Bean
    RestClientHttpServiceGroupConfigurer registrarPostsGroupConfigurer(
            @Value("${posts.base-url:https://jsonplaceholder.typicode.com}") String baseUrl) {
        return groups -> groups
                .filterByName("posts")
                .forEachClient((_, builder) -> builder.baseUrl(baseUrl));
    }

    // users는 더 빠른 응답을 기대하므로 1초로 덮어씀
    @Bean
    RestClientHttpServiceGroupConfigurer registrarUsersGroupConfigurer(
            @Value("${users.base-url:https://jsonplaceholder.typicode.com}") String baseUrl,
            @Qualifier("usersClientHttpRequestFactory") ClientHttpRequestFactory factory) {
        return groups -> groups
                .filterByName("users")
                .forEachClient((_, builder) -> builder
                        .baseUrl(baseUrl)
                        .requestFactory(factory)
                        .defaultHeader("X-Api-Version", "2"));
    }
}
