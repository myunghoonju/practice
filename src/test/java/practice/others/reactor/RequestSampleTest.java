package practice.others.reactor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import practice.others.reactor.mock.data.ReactiveRepository;
import practice.others.reactor.mock.data.ReactiveUserRepository;
import practice.others.reactor.mock.data.User;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class RequestSampleTest {

    ReactiveRepository<User> repository = new ReactiveUserRepository();

    PrintStream originalConsole = System.err;
    ByteArrayOutputStream logConsole;
    String threadInfos = "\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\s{1}\\[\\S+\\]\\s{1}(INFO)\\s{2}(reactor\\.Flux\\.Zip\\.1)\\s{1}-\\s{1}";

    @BeforeEach
    public void beforeEach() {
        logConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(logConsole));
    }

    @AfterEach
    public void afterEach() {
        originalConsole.println(logConsole.toString());
        System.setOut(originalConsole);
    }

    @Test
    void requestAll() {
        StepVerifier.create(repository.findAll())
                .expectSubscription()
                .thenRequest(Long.MAX_VALUE)
                .expectNextCount(4)
                .expectComplete();
    }

    @Test
    void requestCancel() {
        StepVerifier
                .create(repository.findAll())
                .thenRequest(1).expectNext(User.SKYLER)
                .thenRequest(1).expectNext(User.JESSE)
                .thenCancel();
    }

    @Test
    void requestLog() {
        Flux<User> flux = repository.findAll().log();
        StepVerifier.create(flux, 0)
                    .thenRequest(1)
                    .expectNextMatches(u -> true)
                    .thenRequest(1)
                    .expectNextMatches(u -> true)
                    .thenRequest(2)
                    .expectNextMatches(u -> true)
                    .expectNextMatches(u -> true)
                    .verifyComplete();

        logConsole.toString().replaceAll(threadInfos, "");
    }

    @Test
    void requestDebug() {
        Flux<User> begin = repository.findAll()
                                     .doOnSubscribe(s -> System.err.println("Starring:"))
                                     .doOnNext(u -> System.err.println(u.getFirstname() + " " + u.getLastname()))
                                     .doOnComplete(() -> System.err.println("The end!"));

        StepVerifier.create(begin).expectNextCount(4).verifyComplete();
    }
}