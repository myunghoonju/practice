package practice.others.thread.daemon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaemonSample {

    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                log.info("user thread start");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    log.info("daemon thread start");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();

        userThread.join();

        log.info("main thread end");
    }
}
