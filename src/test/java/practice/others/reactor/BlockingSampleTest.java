package practice.others.reactor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import practice.others.reactor.mock.data.ReactiveRepository;
import practice.others.reactor.mock.data.ReactiveUserRepository;
import practice.others.reactor.mock.data.User;

public class BlockingSampleTest {

    ReactiveRepository<User> repository = new ReactiveUserRepository();

    @Test
    void testBlockingSample() {
        User user = repository.findFirst().block();
        assertEquals(User.SKYLER, user);
    }

    @Test
    void testBlockingSample2() {
        Iterable<User> iter = repository.findAll().toIterable();
        Iterator<User> it = iter.iterator();
        assertThat(it.next()).isEqualTo(User.SKYLER);
        assertThat(it.next()).isEqualTo(User.JESSE);
        assertThat(it.next()).isEqualTo(User.WALTER);
        assertThat(it.next()).isEqualTo(User.SAUL);
    }
}
