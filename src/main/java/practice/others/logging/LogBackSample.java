package practice.others.logging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogBackSample {

    public static void main(String[] args) {
        log.debug("Example log from {}", LogBackSample.class.getSimpleName());
    }
}
