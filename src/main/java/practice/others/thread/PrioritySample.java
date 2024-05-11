package practice.others.thread;

public class PrioritySample {

    public static void main(String[] args) throws InterruptedException {
        Count max = new Count("max", Thread.MAX_PRIORITY);
        Count norm = new Count("norm", Thread.NORM_PRIORITY);
        Count min = new Count("min", Thread.MIN_PRIORITY);

        max.start();
        norm.start();
        min.start();

        max.join();
        norm.join();
        min.join();

        System.err.println("complete");
    }

    static class Count extends Thread {
        private String name;
        private int cnt;

        public Count(String name, int priority) {
            this.name = name;
            setPriority(priority);
        }

        @Override
        public void run() {
            while (cnt < 90_000_000) {
                cnt++;
            }
            System.err.println(name + " " + cnt);
        }
    }
}
