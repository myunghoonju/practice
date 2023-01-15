package practice.others.thread.mdc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ParentMDc {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public ParentMDc() {
        MDC.put("IP", "192.168.1.1");
    }

    public void runMultiThreadByExecutor() throws Exception {
        log.info("before start child thread");
        executorService.submit(new ChildMDc());
        ChildMDc childMDc = new ChildMDc();
        childMDc.run();
        log.info("after start child thread");
        executorService.shutdown();
        // while running in the child thread
        boolean result = executorService.awaitTermination(3, TimeUnit.SECONDS);
        log.info("executor's dead? {}", result);
    }

    public static void main(String[] args) throws Exception {
        ParentMDc parentMDc = new ParentMDc();
        parentMDc.runMultiThreadByExecutor();
    }
}
