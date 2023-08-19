package practice.others;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Slf4j
public class ByteArrayTest {

    static byte[] one;
    static byte[] two;

    @BeforeAll
    static void setData() {
        String INPUT_ONE = "I AM A STRING";
        String INPUT_TWO = "I AM NOT A STRING";
        one = INPUT_ONE.getBytes();
        two = INPUT_TWO.getBytes();
    }

    @Test
    void compare_byte_arrays_false_check() {
        log.info("false_check result 1 - {}", one.equals(two));
    }

    @Test
    void compare_byte_arrays_false_check_2() {
        log.info("false_check result 2 - {}", one == two);
    }

    @Test
    void compare_byte_arrays_correct_check() {
        log.info("correct_check result - {}", Arrays.equals(one, two));
    }
}
