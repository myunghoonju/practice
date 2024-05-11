package practice.others.thread;

public class ThreadExceptionSample {

    public static void main(String[] args) {
        neverPrint();

        // will print
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + " error occurred " + e);
            }
        });

        Thread t1 = new Thread(() -> {
            throw new RuntimeException("thread 1 error");
        });

        Thread t2 = new Thread(() -> {
            throw new RuntimeException("thread 2 error");
        });

        t1.start();
        t2.start();

        // will print 2
        Thread t3 = new Thread(() -> {
            System.out.println("error occurred!! and will print 2");
            throw new RuntimeException("wow");
        });

        t3.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " wow error printing" + e);
        });

        t3.start();
    }

    private static void neverPrint() {
        try {
            new Thread(() -> {
                throw new RuntimeException("error occurred");
            }).start();
        } catch (Exception e) {
            notWorking(e);
        }
    }

    private static void notWorking(Exception e) {
        System.out.println("never print - " + e);
    }
}
