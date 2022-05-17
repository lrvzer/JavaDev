package per.study.thread.design.chapter1;

public class SingletonObject4 {

    private Object obj;
    private static SingletonObject4 instance;

    private SingletonObject4() {
        // empty
        int i = 0;
        int j = 10;

        // i=0m j=10;
        // obj
    }

    // double check
    public static SingletonObject4 getInstance() {
        if (instance == null) {
            synchronized (SingletonObject4.class) {
                if (instance == null) {
                    instance = new SingletonObject4();
                }
            }
        }
        return instance;
    }
}
