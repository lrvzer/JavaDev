package per.study.juc.design.chapter8;

public interface Future<T> {
    T get() throws InterruptedException;
}
