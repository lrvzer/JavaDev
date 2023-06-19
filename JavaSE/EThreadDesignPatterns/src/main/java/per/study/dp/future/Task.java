package per.study.dp.future;

/**
 * 用于提供给调用者实现计算逻辑使用，可以接受一个参数并且返回最终的计算结果
 **/
@FunctionalInterface
public interface Task<K, V> {
    /**
     * 给定一个参数，经过计算返回结果
     *
     * @param k
     * @return
     */
    V get(K k);
}
