package per.study.thread.base.chapter7;

public class SynchronizedStaticTest {

    public static void main(String[] args) {
        new Thread(() -> {
            SynchronizedStatic.m1();
        }, "T1").start();

        new Thread(() -> {
            SynchronizedStatic.m2();
        }, "T2").start();

        new Thread(() -> {
            SynchronizedStatic.m3();
        }, "T3").start();
    }
}
