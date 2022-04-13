package per.study.juc.design.chapter1;

public class SingletonObject3 {

    private static SingletonObject3 instance;

    private SingletonObject3() {
        // empty
    }

    // 性能不好
    public synchronized static SingletonObject3 getInstance() {
        if (instance == null)
            instance = new SingletonObject3();
        return instance;
    }
}
