package practice.junit;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
public class ParameterTest {

    static Collection<Object[]> primeNumbers() {
        return Arrays.asList(new Object[][] {
            {2, true},
            {6, false},
            {19, true},
            {22, false},
            {23, true}
        });
    }

    @ParameterizedTest
    @MethodSource("primeNumbers")
    void validateTest(int inputNumber, boolean expected) {
        log.info("Parameterized number:: {}", inputNumber);
        Assertions.assertThat(expected).isEqualTo(validate(inputNumber));
    }

    Boolean validate(final int primeNumber) {
        for (int i = 2; i < (primeNumber / 2); i++) {
            if (primeNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
}
