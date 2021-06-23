package _13_优先级队列;

enum OperationQueuePriority {
    VeryLow, Low, Normal, High, VeryHigh
}

public class Operation implements Comparable<Operation> {
    public String operationName;
    public OperationQueuePriority queuePriority;

    public Operation(OperationQueuePriority queuePriority, String operationName) {
        this.queuePriority = queuePriority;
        this.operationName = operationName;
    }

    public Operation(OperationQueuePriority queuePriority) {
        this(queuePriority, "");
    }

    @Override
    public int compareTo(Operation o) {
        return this.queuePriority.ordinal() - o.queuePriority.ordinal();
    }
}
