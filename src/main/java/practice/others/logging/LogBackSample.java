package practice.others.logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@Slf4j
public class LogBackSample {

    private static final Marker LOG_TYPE_AGENCY = MarkerFactory.getMarker("AGENCY");

    public static void main(String[] args) {
       wrapIsMethod();
    }

    public static void wrapIsMethod() {
        log.info(LOG_TYPE_AGENCY, "Example marked log from {}", LogBackSample.class.getSimpleName());
    }
}
