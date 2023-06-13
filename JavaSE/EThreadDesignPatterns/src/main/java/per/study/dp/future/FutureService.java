package per.study.dp.future;


/**
 * 主要用于提交任务，提交的任务主要有两种：
 * 第一种不需要返回值；
 * 第二种则需要获得最终的计算结果；
 *
 * @param <IN>
 * @param <OUT>
 */
public interface FutureService<IN, OUT> {
    // 提交不需要返回值的任务，Future.get方法返回的将会是null
    Future<?> submit(Runnable runnable);

    // 提交需要返回值的任务，其中Task接口代替了Runnable接口
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    // 使用静态方法创建一个FutureService的实现
    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }
}
