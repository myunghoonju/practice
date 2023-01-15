package practice.others.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomRecursiveTaskTest {

    private int[] arr;
    private CustomRecursiveTask customRecursiveTask;

    @BeforeEach
    void init() {
        Random random = new Random();
        arr = new int[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(35);
        }

        customRecursiveTask = new CustomRecursiveTask(arr);
    }

    @Test
    void callForkJoinPool_whenExistsAndExpectedType_thenCorrect() {
        ForkJoinPool one = Pool.forkJoinPool;
        ForkJoinPool two =  Pool.forkJoinPool;

        assertNotNull(one);
        Assertions.assertEquals(2, one.getParallelism());
        Assertions.assertEquals(one, two);
    }

    @Test
    public void callCommonPool_whenExistsAndExpectedType_thenCorrect() {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        ForkJoinPool commonPoolTwo = ForkJoinPool.commonPool();

        assertNotNull(commonPool);
        assertEquals(commonPool, commonPoolTwo);
    }

    @Test
    public void executeRecursiveTask_whenExecuted_thenCorrect() {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        forkJoinPool.execute(customRecursiveTask);
        customRecursiveTask.join();
        assertTrue(customRecursiveTask.isDone());

        forkJoinPool.submit(customRecursiveTask);
        customRecursiveTask.join();
        assertTrue(customRecursiveTask.isDone());
    }

    @Test
    public void executeRecursiveAction_whenExecuted_thenCorrect() {
        CustomRecursiveAction myRecursiveAction = new CustomRecursiveAction("ddddffffgggghhhh");
        ForkJoinPool.commonPool().invoke(myRecursiveAction);

        assertTrue(myRecursiveAction.isDone());

    }

    @Test
    public void executeRecursiveTaskWithFJ_whenExecuted_thenCorrect() {
        for (int a : arr) {
            System.out.println("-> " + a);
        }

        CustomRecursiveTask customRecursiveTaskFirst = new CustomRecursiveTask(arr);
        CustomRecursiveTask customRecursiveTaskSecond = new CustomRecursiveTask(arr);
        CustomRecursiveTask customRecursiveTaskLast = new CustomRecursiveTask(arr);

        customRecursiveTaskFirst.fork();
        customRecursiveTaskSecond.fork();
        customRecursiveTaskLast.fork();
        int result = 0;
        result += customRecursiveTaskLast.join();
        result += customRecursiveTaskSecond.join();
        result += customRecursiveTaskFirst.join();

        assertTrue(customRecursiveTaskFirst.isDone());
        assertTrue(customRecursiveTaskSecond.isDone());
        assertTrue(customRecursiveTaskLast.isDone());
        System.out.println(result);
    }
    static class Pool {

        private static ForkJoinPool forkJoinPool = new ForkJoinPool(2);
    }
}