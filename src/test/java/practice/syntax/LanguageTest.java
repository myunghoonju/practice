package practice.syntax;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class LanguageTest {

    @Test
    public void variableTest() {
        var text = "Hello java 11";
        text = "Joe";

        assertEquals(text, "Joe");
    }
}
