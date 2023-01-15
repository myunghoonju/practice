package practice.others.thread.mdc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Map;

@Slf4j
public class ChildMDc implements Runnable {

    private final Map<String, String> contextMap = MDC.getCopyOfContextMap();

    public ChildMDc() {
        MDC.put("IP", "127.0.0.1");
    }

    @Override
    public void run() {
        MDC.setContextMap(contextMap);
        log.info("Running in the child thread");
    }
}
