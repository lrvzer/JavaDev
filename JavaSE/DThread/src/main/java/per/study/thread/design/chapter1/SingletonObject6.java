package per.study.thread.design.chapter1;

public class SingletonObject6 {

    private SingletonObject6() {
    }

    private static class InstanceHolder {
        private static final SingletonObject6 INSTANCE = new SingletonObject6();
    }

    public static SingletonObject6 getInstance() {
        return InstanceHolder.INSTANCE;
    }

}
