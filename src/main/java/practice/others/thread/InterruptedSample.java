package practice.others.thread;

public class InterruptedSample {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.err.println("1 interrupt status - " + Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(5_000);
            } catch (Exception e) {
                System.err.println("2 interrupt status - " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
        });

        thread.start();

        try {
            Thread.sleep(2_000);
        } catch (Exception e) {
            // none to do
        }

        thread.interrupt();
        thread.join();
        System.err.println("3 interrupt status - " + thread.isInterrupted());
    }
}
