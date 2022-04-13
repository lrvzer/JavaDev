package per.study.juc.design.chapter10;

import java.util.HashMap;
import java.util.Map;

/**
 * 始终以当前线程作为key值
 * @param <T>
 */
public class ThreadLocalSimulator<T> {
    
    private final Map<Thread, T> storage = new HashMap<>();
    
    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            storage.put(key, t);
        }
    }
    
    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T ret = storage.get(key);
            if (ret == null) 
                return initialValue();
            return ret;
        }
    }

    public T initialValue() {
        return null;
    }

}
