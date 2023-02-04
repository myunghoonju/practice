package practice.others.logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@Slf4j
public class LogBackSample {

    private static final Marker marker = MarkerFactory.getMarker("TEST");

    public static void main(String[] args) {
        isMethod();
    }

    static void isMethod() {
        log.info(marker, "Example marked log from {}", LogBackSample.class.getSimpleName());
        throw new RuntimeException();
    }
}
