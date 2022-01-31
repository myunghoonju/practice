package practice.junit;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(Parameterized.class)
public class ParameterizedTest {

    @Parameter(0)
    public int inputNumber;
    @Parameter(1)
    public boolean expected;

   /*

    private int inputNumber;
    private boolean expected;

    public ParameterizedTest(int inputNumber, boolean expected) {
        this.inputNumber = inputNumber;
        this.expected = expected;
    }

    */

    @Parameters
    public static Collection<Object[]> primeNumbers() {
        return Arrays.asList(new Object[][] {
            {2, true},
            {6, false},
            {19, true},
            {22, false},
            {23, true}
        });
    }

    @Test
    public void validateTest() {
        log.info("Parameterized number:: {}", inputNumber);
        assertThat(expected).isEqualTo(validate(inputNumber));
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
