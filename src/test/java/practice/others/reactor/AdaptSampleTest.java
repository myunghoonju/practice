package practice.others.reactor;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import practice.others.reactor.mock.data.ReactiveRepository;
import practice.others.reactor.mock.data.ReactiveUserRepository;
import practice.others.reactor.mock.data.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class AdaptSampleTest {

    ReactiveRepository<User> repository = new ReactiveUserRepository();

    @Test
    void testAdaptSample() {
        Flowable<User> userFlowable = Flowable.fromPublisher(repository.findAll());
        StepVerifier.create(userFlowable)
                    .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                    .verifyComplete();

        Flux<User> userFlux = Flux.from(repository.findAll());
        StepVerifier.create(userFlux)
                    .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                    .verifyComplete();
    }

    @Test
    void testAdaptSample2() {
        Observable<User> userFlowable = Observable.fromPublisher(repository.findAll());
        Flux<User> from = Flux.from(userFlowable.toFlowable(BackpressureStrategy.BUFFER));
        StepVerifier.create(from)
                    .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                    .verifyComplete();
    }

    @Test
    void testAdaptSample3() {
        Single<User> userFlowable =  Single.fromPublisher(repository.findFirst());
        Mono<User> from = Mono.from(userFlowable.toFlowable());
        StepVerifier.create(from)
                    .expectNext(User.SKYLER)
                    .verifyComplete();
    }

    @Test
    void testAdaptSample4() {
        CompletableFuture<User> future = repository.findFirst().toFuture();
        StepVerifier.create(Mono.fromFuture(future))
                    .expectNext(User.SKYLER)
                    .verifyComplete();
    }
}
