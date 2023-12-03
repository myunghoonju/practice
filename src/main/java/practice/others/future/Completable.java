package practice.others.future;

import java.util.concurrent.CompletableFuture;
// https://medium.com/javarevisited/exception-handling-with-java-completablefuture-in-spring-boot-256ed4ad877f
// https://mincong.io/2020/05/30/exception-handling-in-completable-future/
public class Completable {

    public Integer generousMethod() throws Exception {
        CompletableFuture<Object> iAmAnError = CompletableFuture.failedFuture(new RuntimeException("I am an error")).exceptionally(error -> {System.out.println(error.getMessage());return 0;});
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(this::errorProneMethod)
                                                             .exceptionally(error -> {
                                                                 System.out.println("error handled because - " + error.getMessage());
                                                                 return 0;
                                                             });
        return future.get() + (int) iAmAnError.get();

    }

    private int errorProneMethod() {
        int meaningless = 0;
        try {
            meaningless = slowMethod();
        } catch (Exception e) {
            System.out.println("slow method called");
        }

        return Integer.parseInt("i like an error") + meaningless;
    }

    private int slowMethod() throws Exception {
        Thread.sleep(3000L);
        return 1;
    }
}
