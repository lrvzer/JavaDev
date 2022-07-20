package per.study.thread.design.chapter18;

import java.util.LinkedList;

public class ActivationQueue {

    private static final int MAX_METHOD_QUEUE_SIZE = 100;

    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {
        this.methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest methodRequest) {
        while (methodQueue.size() >= MAX_METHOD_QUEUE_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.methodQueue.addLast(methodRequest);
        this.notifyAll();
    }

    public synchronized MethodRequest take() {
        while (methodQueue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest methodRequest = this.methodQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }

}
