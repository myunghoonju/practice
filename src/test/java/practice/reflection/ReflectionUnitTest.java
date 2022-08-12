package practice.reflection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ReflectionUnitTest {

    @Test
    public void testOne() {
        log.info("givenObject_whenGetsFieldNamesAtRuntime_thenCorrect");
        final Object person = new Person();
        final Field[] fields = person.getClass().getDeclaredFields();

        final List<String> fieldNames = getFieldNames(fields);
        assertTrue(Arrays.asList("name", "age").containsAll(fieldNames));
    }

    @Test
    public void testTwo() {
        log.info("givenObject_whenGetsClassName_thenCorrect");
        final Object goat = new Goat("goat");
        final Class<?> clazz = goat.getClass();

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("practice.reflection.Goat", clazz.getName());
        assertEquals("practice.reflection.Goat", clazz.getCanonicalName());
    }

    @Test
    public void testThree() throws ClassNotFoundException {
        log.info("givenClassName_whenCreatesObject_thenCorrect");
        final Class<?> clazz = Class.forName("practice.reflection.Goat");

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("practice.reflection.Goat", clazz.getName());
        assertEquals("practice.reflection.Goat", clazz.getCanonicalName());
    }

    @Test
    public void testFour() throws ClassNotFoundException {
        log.info("givenClass_whenRecognisesModifiers_thenCorrect");
        final Class<?> goat = Class.forName("practice.reflection.Goat");
        final Class<?> animal = Class.forName("practice.reflection.Animal");
        final int goatMods = goat.getModifiers();
        final int animalMods = animal.getModifiers();

        assertTrue(Modifier.isPublic(goatMods));
        assertTrue(Modifier.isPublic(animalMods));
        assertTrue(Modifier.isAbstract(animalMods));
    }

    @Test
    public void testFive() {
        log.info("givenClass_whenGetsPackageInfo_thenCorrect");
        final Goat goat = new Goat("goat");
        final Class<?> goatC = goat.getClass();
        final Package pkg = goatC.getPackage();

        assertEquals("practice.reflection", pkg.getName());
    }

    @Test
    public void testSix() {
        log.info("givenClass_whenGetsSuperClass_thenCorrect");

    }

    private static List<String> getFieldNames(Field[] fields) {
        final List<String> fieldNames = new ArrayList<>();

        for (final Field field : fields) {
            fieldNames.add(field.getName());
        }

        return fieldNames;

    }
}
