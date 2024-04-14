package practice.others.reactor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import practice.others.reactor.mock.data.User;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class StepVerifierSampleTest {

    @Test
    void expectFooBarComplete() {
        StepVerifier.create(Flux.just("foo", "bar"))
                    .expectNext("foo", "bar")
                    .verifyComplete();
    }

    @Test
    void expectSkylerJesseComplete() {
        StepVerifier.create(Flux.just(new User("swhite", null, null),
                                      new User("jpinkman", null, null)))
                    .assertNext(t -> Assertions.assertThat(t.getUsername()).isEqualTo("swhite"));
    }
}