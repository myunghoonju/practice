package practice.others.sping7.restclient.proxy;

import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import practice.others.sping7.restclient.dto.Post;

import java.util.List;

@HttpExchange("/posts")
interface PostHttpClient {

    @GetExchange
    @Retryable(maxRetries = 2, delay = 200, multiplier = 2.0, maxDelay = 1000)
    @ConcurrencyLimit(10)
    List<Post> getPosts();

    @GetExchange("/{id}")
    @Retryable(maxRetries = 2, delay = 200, multiplier = 2.0, maxDelay = 1000)
    Post getPost(@PathVariable Long id);
}
