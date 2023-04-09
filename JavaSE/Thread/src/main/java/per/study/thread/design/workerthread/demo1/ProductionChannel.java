package per.study.thread.design.workerthread.demo1;

public class ProductionChannel {

    // 传送带上最多可以有多少代加工的产品
    private static final int MAX_PRODUCTION = 100;

    // 主要用来存放待加工的产品，也就是传送带
    private final Production[] productionQueue;

    // 队列尾
    private int tail;

    // 队列头
    private int head;

    // 当前流水线上有多少待加工的产品
    private int total;

    // 在流水线上有多少个工人
    private final Worker[] workers;

    /**
     * workerSize
     * 创建ProductionChannel需要指定workerSize个流水线工人
     *
     * @param workerSize
     */
    public ProductionChannel(int workerSize) {
        this.productionQueue = new Production[MAX_PRODUCTION];
        this.workers = new Worker[workerSize];

        // 实例化每个工人（Worker）并启动
        for (int i = 0; i < workerSize; i++) {
            workers[i] = new Worker("Worker-" + i, this);
            workers[i].start();
        }
    }

    public void offerProduction(Production production) {
        synchronized (this) {
            // 当传送带上待加工的产品超过了最大值时需要阻塞上游再次传送产品
            while (total >= productionQueue.length) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 将产品放到传送带，并且通知工人线程工作
            productionQueue[tail] = production;
            tail = (tail + 1) % productionQueue.length;
            total++;
            this.notifyAll();
        }
    }

    public Production takeProduction() {
        synchronized (this) {
            // 当传送带上没有产品时，工人等待着产品从上游输送到传送带上
            while (total <= 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // 获取产品
            Production prod = productionQueue[head];
            head = (head + 1) % productionQueue.length;
            total--;
            this.notifyAll();
            return prod;
        }
    }

}
