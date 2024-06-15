package practice.others.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class FlagInterruptedExceptionThread {

    public static void main(String[] args) throws InterruptedException {
        myThread();
    }

    static void myThread() {
        Thread worker = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    log.info("thread running");
                    log.info("interrupted status 1 {}", Thread.currentThread().isInterrupted());
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                log.info("interrupted status 2 {}", Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }

            log.info("worker thread exit");
            log.info("interrupted status 3 {}", Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
            System.err.print("stop worker thread");
        });

        worker.start();
        stopper.start();
    }
}
