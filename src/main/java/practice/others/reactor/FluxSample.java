package practice.others.reactor;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import reactor.core.publisher.Flux;

public class FluxSample {

    Flux<String> emptyFlux() {
        return Flux.empty();
    }

    Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo", "bar");
    }

    Flux<String> fooBarFluxFromList() {
        return Flux.fromIterable( new ArrayList<>(Arrays.asList("foo", "bar")));
    }

    Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException());
    }

    Flux<Long> counter() {
        Duration period = Duration.of(100L, ChronoUnit.MILLIS);
        return Flux.interval(period).take(10L);
    }
}
