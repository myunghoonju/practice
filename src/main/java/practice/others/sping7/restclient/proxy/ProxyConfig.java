package practice.others.sping7.restclient.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

// Spring 6 방식: HttpServiceProxyFactory로 프록시를 수동 생성
@Configuration
@EnableResilientMethods
class ProxyConfig {

    @Bean
    RestClient postRestClient() {
        return RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    @Bean
    PostHttpClient postHttpClient(RestClient postRestClient) {
        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(postRestClient))
                .build()
                .createClient(PostHttpClient.class);
    }
}
