package per.study.thread.design.workerthread.demo2;

import java.util.Arrays;

public class Channel {

    /*流水线上的最大货物*/
    private static final int MAX_REQUEST = 100;

    /*货物*/
    private final Request[] requestQueue;

    // 队头
    private int head;
    // 队尾
    private int tail;
    // 当前通道上的货物
    private int count;

    private final WorkerThread[] workerPool;

    public Channel(int workers) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new WorkerThread[workers];
        this.init();
    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    /**
     * push switch to start all of worker's work
     */
    public void startWorker() {
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }

    public synchronized void offer(Request request) {
        while (count >= requestQueue.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.requestQueue[tail] = request;
        this.tail = (tail + 1) % requestQueue.length;
        this.count++;
        this.notifyAll();
    }

    public synchronized Request take() {
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = this.requestQueue[head];
        this.head = (head + 1) % requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }

}
