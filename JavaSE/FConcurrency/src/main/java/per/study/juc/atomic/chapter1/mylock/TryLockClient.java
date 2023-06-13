package per.study.juc.atomic.chapter1.mylock;

public class TryLockClient {

    private final static CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) {

        // 两个线程状态 TIMED_WAITING    BLOCKED
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
//                    doSomething();
                    doSomething2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (GetLockException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * synchronized
     * @throws InterruptedException
     */
    private static void doSomething() throws InterruptedException {
        synchronized (TryLockClient.class) {
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100000);
        }
    }

    private static void doSomething2() throws InterruptedException, GetLockException {
        try {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100000);
        } finally {
            lock.unlock();
        }
    }

}
