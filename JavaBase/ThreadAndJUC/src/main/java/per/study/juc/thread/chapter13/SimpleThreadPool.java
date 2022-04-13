package per.study.juc.thread.chapter13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool extends Thread {

    /* 线程池大小 */
    private int size;
    /* 任务队列大小 */
    private final int queueSize;
    /* 默认线程池大小 */
    private static final int DEFAULT_SIZE = 10;
    /* 默认任务队列大小 */
    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;

    /* 线程数 */
    private static int seq = 0;

    /* 任务队列 */
    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    /* 线程队列 */
    private static final List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    /* 线程名字前缀 */
    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL";
    /* 线程组 */
    private static final ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    /* 线程池状态 */
    private volatile boolean destroy = false;

    private int min;
    private int max;
    private int active;

    /* 拒绝策略 */
    private final DiscardPolicy discardPolicy;
    public static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard This Task.");
    };

    public SimpleThreadPool() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    /* 初始化线程池 */
    private void init() {
        for (int i = 0; i < this.min; i++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    /* 提交任务 */
    public void submit(Runnable runnable) {
        if (destroy) throw new IllegalStateException("The thread pool already destroy and not allow submit task");
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize)
                discardPolicy.discard();

            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("Pool#Min:%d, Active:%d, Max:%d, Current:%d, QueueSize:%d\n",
                    this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(5_000L);

                if (TASK_QUEUE.size() > active && size < active) { /* 第一次扩充线程数量 */
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    System.out.println("=== The pool incremented to active. ===");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) { /* 第二次扩充线程数量 */
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    System.out.println("=== The pool incremented to max. ===");
                    size = max;
                }

                /* 释放线程数量 */
                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        System.out.println("============Reduce=============");
                        int releaseSize = size - active;
                        Iterator<WorkerTask> it = THREAD_QUEUE.iterator();
                        while (it.hasNext()) {
                            if (releaseSize <= 0)
                                break;
                            WorkerTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    /* 关闭线程池 */
    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }

        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkerTask task : THREAD_QUEUE) {
                    if (task.getTaskState() == TaskState.BLOCKED) {
                        task.interrupt();
                        task.close();
                        initVal--;
                    } else {
                        Thread.sleep(10);
                    }
                }
            }
        }
        this.destroy = true;
        System.out.println("The thread pool disposed.");
    }

    public int getSize() {
        return size;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isDestroy() {
        return this.destroy;
    }

    /* 线程状态 */
    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    @FunctionalInterface
    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    private static class WorkerTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return this.taskState;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    /* 任务队列为空，当前线程等待 */
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Closed");
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }

                if (runnable != null) {
                    taskState = TaskState.RUNNING; /* 运行中 */
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool threadPool = new SimpleThreadPool();

        for (int i = 0; i < 40; i++) {
            threadPool.submit(() -> {
                System.out.println("The runnable be serviced by " + Thread.currentThread() + " start.");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable be serviced by " + Thread.currentThread() + " finished.");
            });
        }

        Thread.sleep(20000);
        threadPool.shutdown();
//        threadPool.submit(() -> System.out.println("====="));
    }
}