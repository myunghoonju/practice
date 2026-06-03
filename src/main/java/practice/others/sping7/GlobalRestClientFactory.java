package practice.others.sping7;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
public class GlobalRestClientFactory {

    @Bean
    ClientHttpRequestFactory clientHttpRequestFactory() {
        return apacheFactory(2);
    }

    @Bean
    ClientHttpRequestFactory usersClientHttpRequestFactory() {
        return apacheFactory(1);
    }

    private ClientHttpRequestFactory apacheFactory(int timeoutSeconds) {
        return new HttpComponentsClientHttpRequestFactory(
                HttpClients.custom()
                            .setDefaultRequestConfig(RequestConfig.custom().setResponseTimeout(Timeout.ofSeconds(timeoutSeconds)).build())
                            .build()
        );
    }
}
