package practice.others.future;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CompletableTest {

    @Test
    void error_handle_test() throws Exception {
        Completable completable = new Completable();
        assertThat(completable.generousMethod()).isEqualTo(0);
    }
}
