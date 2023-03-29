package per.study.thread.base.chapter8;

public class DeadLock {
    private final OtherService otherService;

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    private static final Object lock = new Object();

    public void m1() {
        synchronized (lock) {
            System.out.println("m1======");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (lock) {
            System.out.println("m2======");
        }
    }

}
