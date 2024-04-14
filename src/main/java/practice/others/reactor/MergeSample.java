package practice.others.reactor;

import practice.others.reactor.mock.data.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MergeSample {

    Mono<User> upperCase(Mono<User> users) {
        return users.map(u -> new User(u.getUsername().toUpperCase(),
                                       u.getFirstname().toUpperCase(),
                                       u.getLastname().toUpperCase()));
    }

    Flux<User> upperCase2(Flux<User> flux) {
        return flux.map( u -> new User(u.getUsername().toUpperCase(),
                                       u.getFirstname().toUpperCase(),
                                       u.getLastname().toUpperCase()));
    }

    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap(this::asyncCapitalizeUser);
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

    Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.mergeWith(flux2);
    }

    Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.concatWith(flux2);
    }

    Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {
        return Flux.concat(mono1, mono2);
    }
}
