package practice.others.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadGroupEx {

    public static void main(String[] args) throws InterruptedException {}

    static void test() {
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup customThreadGroup = new ThreadGroup("customThreadGroup");

        Thread defaultGroupThread = new Thread(new GroupRunnable(), "defaultGroupThread");

        Thread mainGroupThread = new Thread(mainThreadGroup, new GroupRunnable(), "mainGroupThread");

        Thread customGroupThread = new Thread(customThreadGroup, new GroupRunnable(), "customGroupThread");

        defaultGroupThread.start();
        mainGroupThread.start();
        customGroupThread.start();
    }

    static void nestedThreadGroup() throws InterruptedException {
        ThreadGroup highestThreadGroup = new ThreadGroup("highest thread group");
        ThreadGroup subGroupThread = new ThreadGroup(highestThreadGroup, "lowest thread group");
        Thread highestThreadGroupThread = new Thread(highestThreadGroup, new GroupRunnable(), "highestThreadGroup thread");
        Thread subThreadGroupThread = new Thread(subGroupThread, new GroupRunnable(), "subGroupThread thread");

        highestThreadGroupThread.start();
        subThreadGroupThread.start();

        Thread.sleep(1000);

        log.info("------");
        log.info("highest thread group info");
        highestThreadGroup.list();

    }

    static void scope() throws InterruptedException {
        ThreadGroup topGroup = new ThreadGroup("상위그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위그룹");

        Thread topGroupThread = new Thread(topGroup, () -> {
            log.info("상위 그룹 스레드에서 하위 그룹의 최대 우선순위 설정 변경 전 {}", subGroup.getMaxPriority());

            subGroup.setMaxPriority(7);

            log.info("상위 그룹 스레드에서 하위 그룹의 최대 우선순위 설정 변경 후 {}", subGroup.getMaxPriority());
        }, "상위 스레드 그룹");

        Thread lowGroupThread = new Thread(subGroup, () -> {
            log.info("하위 그룹 스레드에서 상위 그룹의 최대 우선순위 설정 변경 전 {}", topGroup.getMaxPriority());

            topGroup.setMaxPriority(3);

            log.info("하위 그룹 스레드에서 상위 그룹의 최대 우선순위 설정 변경 후 {}", topGroup.getMaxPriority());
        }, "하위 스레드 그룹");

        topGroupThread.start();
        lowGroupThread.start();

        topGroupThread.join();
        lowGroupThread.join();

        // 스레드 생성시 런타임에 변경한 우선순위는 적용안됨(그룹우선순위는 변경됨)
        log.info("top group thread info {} {}", topGroupThread.getName(), topGroupThread.getPriority());
        log.info("low group thread info {} {}", lowGroupThread.getName(), lowGroupThread.getPriority());

        // 하지만 새로 만든 스레드에서 우선순위는 변경된 상위 그룹 우선순위를 모두 가진다
        Thread user1 = new Thread(topGroup, () -> {
        }, "user thread 1");
        Thread user2 = new Thread(subGroup, () -> {
        }, "user thread 2");

        user1.start();
        user2.start();

        user1.setPriority(7); // not applied

        user1.join();
        user2.join();

        log.info("top group thread info {} {}", user1.getName(), user1.getPriority());
        log.info("low group thread info {} {}", user2.getName(), user2.getPriority());
    }

    static void scope2() throws InterruptedException {
        ThreadGroup topGroup = new ThreadGroup("top group");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "sub group");

        Thread top = new Thread(topGroup, () -> {
            while (true) {
                log.info("상위 스레드 그룹 실행 중");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "상위 그룹 스레드");

        Thread bottom = new Thread(subGroup, () -> {
            while (true) {
                log.info("하위 스레드 그룹 실행 중");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "하위 그룹 스레드");

        top.start();
        bottom.start();

        Thread.sleep(3000);

        log.info("stop group thread");

        top.interrupt();
        bottom.interrupt();
    }

    static class GroupRunnable implements Runnable {
        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            log.info("group of currentThread = {}", currentThread.getThreadGroup().getName());
        }
    }
}
