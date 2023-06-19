package per.study.dp.future;

public class FutureTask<T> implements Future<T> {
    // 计算结果
    private T result;
    // 用于记录任务是否完成
    private boolean isDone = false;

    // 定义对象锁
    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {
        synchronized (LOCK) {
            // 当任务还没完成时，调用get方法会被挂起而进入阻塞
            while (!isDone) {
                LOCK.wait();
            }
            return result;
        }
    }

    protected void finish(T result) {
        synchronized (LOCK) {
            // balking 设计模式
            if (isDone) return;

            // 计算完成，为result指定结果，并将isDone设为true，同时唤醒阻塞中的线程
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }


    /**
     * 任务是否完成
     */
    @Override
    public boolean done() {
        return isDone;
    }
}
