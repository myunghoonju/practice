package practice.others.reactor;

import org.junit.jupiter.api.Test;

import practice.others.reactor.mock.data.ReactiveRepository;
import practice.others.reactor.mock.data.ReactiveUserRepository;
import practice.others.reactor.mock.data.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class TransformSampleTest {

    MergeSample mergeSample = new MergeSample();
    ReactiveRepository<User> repository = new ReactiveUserRepository();

    @Test
    void capitalizeOne() {
        Mono<User> mono = repository.findFirst();
        StepVerifier.create(mergeSample.upperCase(mono))
                    .expectNext(new User("SWHITE", "SKYLER", "WHITE"))
                    .verifyComplete();
    }

    @Test
    public void transformFlux() {
        Flux<User> flux = repository.findAll();
        StepVerifier.create(mergeSample.upperCase2(flux))
                    .expectNext(
                            new User("SWHITE", "SKYLER", "WHITE"),
                            new User("JPINKMAN", "JESSE", "PINKMAN"),
                            new User("WWHITE", "WALTER", "WHITE"),
                            new User("SGOODMAN", "SAUL", "GOODMAN"))
                    .verifyComplete();
    }

    @Test
    public void  asyncTransformFlux() {
        Flux<User> flux = repository.findAll();
        StepVerifier.create(mergeSample.asyncCapitalizeMany(flux))
                    .expectNext(
                            new User("SWHITE", "SKYLER", "WHITE"),
                            new User("JPINKMAN", "JESSE", "PINKMAN"),
                            new User("WWHITE", "WALTER", "WHITE"),
                            new User("SGOODMAN", "SAUL", "GOODMAN"))
                    .verifyComplete();
    }
}