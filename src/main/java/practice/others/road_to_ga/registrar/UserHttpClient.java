package practice.others.road_to_ga.registrar;

import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange("/users")
public interface UserHttpClient {

    @GetExchange
    @Retryable(maxRetries = 2, delay = 500, multiplier = 2.0, maxDelay = 3000)
    @ConcurrencyLimit(5)
    List<User> getUsers();

    @GetExchange("/{id}")
    @Retryable(maxRetries = 2, delay = 500, multiplier = 2.0, maxDelay = 3000)
    User getUser(@PathVariable Long id);

    record User(Long id, String name, String email, String phone) {}
}
