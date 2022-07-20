package per.study.juc.atomic.chapter2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe me = new TestMe();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                final int MAX = 20;
                for (int j = 0; j < MAX; j++) {
                    int v = updater.getAndIncrement(me);
                    System.out.println(Thread.class.getName() + " => " + v);
                }
            }).start();
        }
    }

    static class TestMe {
        volatile int i;
    }
}
