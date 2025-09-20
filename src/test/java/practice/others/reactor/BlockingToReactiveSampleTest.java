package practice.others.reactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import practice.others.reactor.mock.data.BlockingRepository;
import practice.others.reactor.mock.data.BlockingUserRepository;
import practice.others.reactor.mock.data.ReactiveRepository;
import practice.others.reactor.mock.data.ReactiveUserRepository;
import practice.others.reactor.mock.data.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

public class BlockingToReactiveSampleTest {

    BlockingRepository repository = new BlockingUserRepository();

    @Test
    void select() {
        Flux<User> flux = Flux.defer(() -> Flux.fromIterable(repository.findAll())).subscribeOn(Schedulers.boundedElastic());
        assertThat(repository.callCount()).isEqualTo(0).withFailMessage("The call to findAll must be deferred until the flux is subscribed");
        StepVerifier.create(flux)
                    .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                    .verifyComplete();

        assertThat(repository.callCount()).isEqualTo(1).withFailMessage("The call to findAll must be deferred until the flux is subscribed");
    }

//    @Test
//    void save() {
//        ReactiveRepository<User> reactiveRepository = new ReactiveUserRepository();
//        Mono<Void> complete = reactiveRepository.findAll().publishOn(Schedulers.elastic()).doOnNext(repository::save).then();
//
//        assertThat(repository.callCount()).isEqualTo(0);
//
//        StepVerifier.create(complete).verifyComplete();
//        Iterator<User> it = repository.findAll().iterator();
//        assertThat(it.next()).isEqualTo(User.SKYLER);
//        assertThat(it.next()).isEqualTo(User.JESSE);
//        assertThat(it.next()).isEqualTo(User.WALTER);
//        assertThat(it.next()).isEqualTo(User.SAUL);
//    }
}
