package per.study.thread.design.chapter9;

import java.util.LinkedList;

public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.getFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.add(request);
            queue.notifyAll();
        }
    }
}
