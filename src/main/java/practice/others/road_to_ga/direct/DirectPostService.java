package practice.others.road_to_ga.direct;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.retry.RetryException;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import practice.others.road_to_ga.dto.Post;

import java.time.Duration;
import java.util.List;

// RestClient를 직접 호출하는 명령형 방식
// retry는 RetryTemplate으로 프로그래밍형으로 처리
@Service
class DirectPostService {

    private final RestClient restClient;
    private final RetryTemplate retryTemplate;

    DirectPostService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        var policy = RetryPolicy.builder()
                .maxRetries(2)
                .delay(Duration.ofMillis(200))
                .multiplier(2.0)
                .maxDelay(Duration.ofSeconds(1))
                .build();

        this.retryTemplate = new RetryTemplate(policy);
    }

    List<Post> getPosts() throws RetryException {
        return retryTemplate.execute(() ->
                restClient.get()
                        .uri("/posts")
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {})
        );
    }

    Post getPost(Long id) throws RetryException {
        return retryTemplate.execute(() ->
                restClient.get()
                        .uri("/posts/{id}", id)
                        .retrieve()
                        .body(Post.class)
        );
    }
}
