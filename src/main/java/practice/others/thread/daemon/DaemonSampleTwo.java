package practice.others.thread.daemon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaemonSampleTwo {

    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            new Thread(() -> {
                log.info("is child of user thread daemon? {}", Thread.currentThread().isDaemon());
            }).start();

            log.info("user thread daemon? {}", Thread.currentThread().isDaemon());
        });

        Thread daemonThread = new Thread(() -> {
            new Thread(() -> {
                log.info("is child of daemon thread daemon? {}", Thread.currentThread().isDaemon());
            }).start();
            log.info("daemon thread daemon? {}", Thread.currentThread().isDaemon());
        });

        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();

        userThread.join();

        log.info("main thread end");
    }
}
