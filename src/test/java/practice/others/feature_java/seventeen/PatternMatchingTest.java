package practice.others.feature_java.seventeen;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * java 17 features<br>
 * https://www.swtestacademy.com/java-17-features/
 */
@Slf4j
public class PatternMatchingTest {

    @Test
    void instanceOfPatternMatching() {
        Object objAsString = "String as an object";
        Object objAsInt = 123;

        if (objAsString instanceof String string) {
            log.info(string.toUpperCase());
        }

        if (objAsString instanceof String str && !str.isEmpty()) {
            log.info(str.toUpperCase());
        }

        if (objAsInt instanceof Integer number) {
            log.info("number :: {}", number);
        }

        if (!(objAsInt instanceof String string)) {
            log.info("not a string");
        }
    }
}