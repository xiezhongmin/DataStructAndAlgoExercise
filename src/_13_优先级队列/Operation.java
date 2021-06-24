package _13_优先级队列;

enum OperationQueuePriority {
    VeryLow, Low, Normal, High, VeryHigh
}

public class Operation implements Comparable<Operation> {
    public String operationName;
    public OperationQueuePriority queuePriority;
    private boolean cancelled;
    private boolean executing;
    private boolean finished;
    private Task task;

    public interface Task {
        void execute();
    }

    public boolean cancelled() {
        return cancelled;
    }

    public boolean executing() {
        return executing;
    }

    public boolean finished() {
        return finished;
    }

    public void start() {
        if (cancelled || task == null) {
            return;
        }

        executing = true;
        task.execute();
        executing = false;
        finished = true;
    }

    public void cancel() {
        cancelled = true;
    }

    public Operation(OperationQueuePriority queuePriority, Task task, String operationName) {
        install();
        this.queuePriority = queuePriority;
        this.task = task;
        this.operationName = operationName;
    }

    public Operation(OperationQueuePriority queuePriority, Task task) {
       this(queuePriority, task, "");
    }

    public Operation(Task task) {
        this(OperationQueuePriority.Normal, task, "");
    }

    private void install() {
        executing = false;
        cancelled = false;
        finished = false;
    }

    @Override
    public int compareTo(Operation o) {
        return this.queuePriority.ordinal() - o.queuePriority.ordinal();
    }
}
