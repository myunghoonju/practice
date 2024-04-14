package practice.others.reactor;

import org.junit.jupiter.api.Test;

import practice.others.reactor.mock.data.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class ErrorSampleTest {

    @Test
    void testErrorSample() {
        Mono<Object> sgoodman = Mono.error(new IllegalStateException()).onErrorReturn(User.SAUL);
        User user = (User) sgoodman.block();
        System.out.println(user);

        User swhite = Mono.just(User.SKYLER).onErrorReturn(User.SAUL).block();
        System.out.println(swhite);
    }

    @Test
    void testErrorSample2() {
        Flux<Object> resume = Flux.error(new IllegalStateException()).onErrorResume(e -> Flux.just(User.SAUL, User.JESSE));
        StepVerifier.create(resume).expectNext(User.SAUL, User.JESSE).verifyComplete();

        Flux<User> resume1 = Flux.just(User.SAUL, User.WALTER).onErrorResume(e -> Flux.just(User.SAUL, User.JESSE));
        StepVerifier.create(resume1).expectNext(User.SAUL, User.WALTER).verifyComplete();
    }

    @Test
    void testErrorPropagation() {
        Flux<User> flux = capitalizeMany(Flux.just(User.SAUL, User.JESSE));
        StepVerifier.create(flux).verifyError(GetOutOfHereException.class);
    }

    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.handle((u, sink) -> {
            try {
                sink.next(capitalizeUser(u));
            } catch (GetOutOfHereException e) {
                sink.error(e);
            }
        });
    }

    User capitalizeUser(User user) throws GetOutOfHereException {
        if (user.equals(User.SAUL)) {
            throw new GetOutOfHereException();
        }
        return new User(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    static final class GetOutOfHereException extends Exception {}
}