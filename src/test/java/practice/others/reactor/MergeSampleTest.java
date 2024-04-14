package practice.others.reactor;

import org.junit.jupiter.api.Test;

import practice.others.reactor.mock.data.ReactiveRepository;
import practice.others.reactor.mock.data.ReactiveUserRepository;
import practice.others.reactor.mock.data.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class MergeSampleTest {

    static User MARIE = new User("mschrader", "Marie", "Schrader");
    static User MIKE = new User("mehrmantraut", "Mike", "Ehrmantraut");

    MergeSample mergeSample = new MergeSample();
    ReactiveRepository<User> repository = new ReactiveUserRepository(MARIE, MIKE);
    ReactiveRepository<User> repositoryWithDelay = new ReactiveUserRepository(500);


    @Test
    public void mergeWithInterleave() {
        Flux<User> flux = mergeSample.mergeFluxWithInterleave(repositoryWithDelay.findAll(), repository.findAll());
        StepVerifier.create(flux)
                    .expectNext(MARIE, MIKE, User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                    .verifyComplete();
    }

    @Test
    public void mergeWithNoInterleave() {
        Flux<User> flux = mergeSample.mergeFluxWithNoInterleave(repositoryWithDelay.findAll(), repository.findAll());
        StepVerifier.create(flux)
                    .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL, MARIE, MIKE)
                    .verifyComplete();
    }

    @Test
    public void multipleMonoToFlux() {
        Mono<User> skylerMono = repositoryWithDelay.findFirst();
        Mono<User> marieMono = repository.findFirst();
        Flux<User> flux = mergeSample.createFluxFromMultipleMono(skylerMono, marieMono);
        StepVerifier.create(flux)
                    .expectNext(User.SKYLER, MARIE)
                    .verifyComplete();
    }
}