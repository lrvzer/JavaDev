package per.study.juc.atomic.chapter2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class ATFUTest {

    private volatile int i;

    private AtomicIntegerFieldUpdater<ATFUTest> updater = AtomicIntegerFieldUpdater.newUpdater(ATFUTest.class, "i");

    public void update(int newValue) {
        updater.compareAndSet(this, i, newValue);
    }

    public int get() {
        return i;
    }

    public static void main(String[] args) {
        ATFUTest test = new ATFUTest();
        test.update(10);
        System.out.println(test.get());;
    }

}
