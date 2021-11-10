package practice.reflection;


import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUnitTest {

    @Test
    public void testOne() {
        final Object person = new Person();
        final Field[] fields = person.getClass().getDeclaredFields();

        final List<String> fieldNames = getFieldNames(fields);
        assertTrue(Arrays.asList("name", "age").containsAll(fieldNames));
    }

    private static List<String> getFieldNames(Field[] fields) {
        final List<String> fieldNames = new ArrayList<>();

        for (final Field field : fields) {
            fieldNames.add(field.getName());
        }

        return fieldNames;

    }
}
