package practice.others.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

@Slf4j
public class CustomRecursiveAction extends RecursiveAction {

    private String workload = "";
    public static final int THREASHOLD = 4;

    public CustomRecursiveAction(String workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (workload.length() > THREASHOLD) {
            ForkJoinTask.invokeAll(createSubTask());
        } else {
            processing(workload);
        }
    }

    private void processing(String workload) {
        String result = workload.toUpperCase();
        log.debug("This result - (" + result + ") - was processed by " + Thread.currentThread().getName());
    }

    private Collection<CustomRecursiveAction> createSubTask() {
        List<CustomRecursiveAction> subtasks = new ArrayList<>();

        String one = workload.substring(0, workload.length() / 2);
        String two = workload.substring(workload.length() / 2, workload.length());

        subtasks.add(new CustomRecursiveAction(one));
        subtasks.add(new CustomRecursiveAction(two));

        return subtasks;
    }
}
