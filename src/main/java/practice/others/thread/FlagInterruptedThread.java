package practice.others.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class FlagInterruptedThread {

    private static final AtomicBoolean run = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        //flag();
        //isInterrupted();
        //interrupted();
    }

    private static void flag() {
        new Thread(() -> {
            int cnt = 0;
            while (run.getAcquire()) {
                cnt++;
            }
            log.info("thread 1 end {}", cnt);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("thread 2 start");
            run.set(false);
        }).start();
    }

    public static void isInterrupted() throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                log.info("thread run");
            }
            log.info("interrupted status {}", Thread.currentThread().isInterrupted());
            log.info("interrupted");
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
            log.info("stopper stopped worker thread");
        });

        worker.start();
        stopper.start();

        worker.join();
        stopper.join();
    }

    public static void interrupted() throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (!Thread.interrupted()) {
                log.info("thread run");
            }
            log.info("interrupted");
            log.info("interrupted status 1 {}", Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            log.info("interrupted status 2 {}", Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
            log.info("stopper stopped worker thread");
        });

        worker.start();
        stopper.start();

        worker.join();
        stopper.join();
    }
}
