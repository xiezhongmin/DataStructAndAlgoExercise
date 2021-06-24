package _13_优先级队列;

public class Main {
    public static void main(String[] args) {
        Operation op1 = new Operation(OperationQueuePriority.VeryLow, new Operation.Task() {
            @Override
            public void execute() {
                System.out.println("op1 execute VeryLow");
            }
        }, "op1");
        Operation op2 = new Operation(OperationQueuePriority.Low, new Operation.Task() {
            @Override
            public void execute() {
                System.out.println("op2 execute Low");
            }
        }, "op2");
        Operation op3 = new Operation(OperationQueuePriority.Normal, new Operation.Task() {
            @Override
            public void execute() {
                System.out.println("op3 execute Normal");
            }
        }, "op3");
        Operation op4 = new Operation(OperationQueuePriority.High, new Operation.Task() {
            @Override
            public void execute() {
                System.out.println("op4 execute High");
            }
        }, "op4");
        Operation op5 = new Operation(OperationQueuePriority.VeryHigh, new Operation.Task() {
            @Override
            public void execute() {
                System.out.println("op5 execute VeryHigh");
            }
        }, "op5");
        Operation op6 = new Operation(OperationQueuePriority.Normal, new Operation.Task() {
            @Override
            public void execute() {
                System.out.println("op6 execute Normal");
            }
        }, "op6");

        PriorityQueue queue = new PriorityQueue();
        queue.enQueue(op3);
        queue.enQueue(op2);
        queue.enQueue(op1);
        queue.enQueue(op5);
        queue.enQueue(op4);
        queue.enQueue(op6);

        while (!queue.isEmpty()) {
            Operation op = queue.front();
            System.out.println(op.operationName + " finished: " + op.finished());
            queue.deQueue();
            System.out.println(op.operationName + " finished: " + op.finished());
            System.out.println("-------------------------");
        }
    }
}
