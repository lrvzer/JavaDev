package per.study.juc.design.chapter1;

/**
 * 线程不安全
 */
public class SingletonObject2 {

    private static SingletonObject2 instance;

    private SingletonObject2() {
        // empty
    }

    public static SingletonObject2 getInstance() {
        if (instance == null)
            instance = new SingletonObject2();
        return instance;
    }
}
