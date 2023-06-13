package per.study.dp.future;

public class FutureTask<T> implements Future<T> {
    // 计算结果
    private T result;
    // 任务是否完成
    private boolean isDone = false;

    @Override
    public T get() throws InterruptedException {
        return null;
    }

    @Override
    public boolean done() {
        return false;
    }
}
