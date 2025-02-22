package per.study.dp.future;

@FunctionalInterface
public interface Callback<T> {
    /**
     * 任务完成后会调用该方法，其中T为任务执行后的结果
     */
    void call(T t);
}
