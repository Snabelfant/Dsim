package dsim.taskrunner;

import dsim.commands.Procedure;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dag on 30.09.2017.
 */
public class TaskRunner {
    private AtomicInteger nextTaskId = new AtomicInteger(0);
    private AtomicInteger noOfActiveTasks = new AtomicInteger(0);
    private ExecutorService executorService;

    public TaskRunner(int maxThreads) {
        executorService = Executors.newFixedThreadPool(maxThreads);
    }

    public int getNoOfActiveTasks() {
        return noOfActiveTasks.get();
    }

    public void submit(Procedure procedure) {
        noOfActiveTasks.getAndIncrement();
        Task task = new Task(procedure);
        executorService.submit(task);
    }

    private class Task implements Runnable {
        private final Procedure procedure;
        private int id;

        Task(Procedure procedure) {
            this.procedure = procedure;
            this.id = nextTaskId.getAndIncrement();
        }

        int getId() {
            return id;
        }

        @Override
        public void run() {
            procedure.execute();
            noOfActiveTasks.decrementAndGet();
            if (procedure.isForever()) {
                executorService.submit(this);
            }
        }
    }

}
