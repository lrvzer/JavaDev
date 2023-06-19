package per.study.dp.future;

import java.util.concurrent.atomic.AtomicInteger;

public class FutureServiceImpl<K, V> implements FutureService<K, V> {
    // 为执行的线程指定名字前缀
    private static final String FUTURE_THREAD_PREFIX = "FUTURE-";
    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> future = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            // 任务执行结束之后将null作为结果传给future
            future.finish(null);
        }, getNextName()).start();
        return future;
    }

    @Override
    public Future<V> submit(Task<K, V> task, K k) {
        final FutureTask<V> future = new FutureTask<>();
        new Thread(() -> {
            V result = task.get(k);
            future.finish(result);
        }, getNextName()).start();
        return future;
    }

    @Override
    public Future<V> submit(Task<K, V> task, K k, Callback<V> callback) {
        final FutureTask<V> future = new FutureTask<>();
        new Thread(() -> {
            V result = task.get(k);
            future.finish(result);
            if (callback != null) {
                callback.call(result);
            }
        }, getNextName()).start();
        return null;
    }
}
