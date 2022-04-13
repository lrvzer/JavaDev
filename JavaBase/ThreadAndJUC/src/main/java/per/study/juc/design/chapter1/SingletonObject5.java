package per.study.juc.design.chapter1;

public class SingletonObject5 {

    /** volatile
     *  保证内存可见性
     *  保证有序性
     */
    private static volatile SingletonObject5 instance;

    private SingletonObject5() {
    }

    // double check add volatile
    public static SingletonObject5 getInstance() {
        if (instance == null) {
            synchronized (SingletonObject5.class) {
                if (instance == null) {
                    instance = new SingletonObject5();
                }
            }
        }
        return instance;
    }
}
