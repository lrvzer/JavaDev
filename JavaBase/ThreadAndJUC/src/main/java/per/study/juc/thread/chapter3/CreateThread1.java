package per.study.juc.thread.chapter3;

import java.util.Arrays;

/**
 * 1.创建线程对象Thread，默认有一个线程名，以“Thread-"开头，从0开始计数
 * 2.如果在构造Thread时，没有传递Runnable或者没有复写Thread的run方法，该Thread将不会调用任何东西；
 *   如果传递了Runnable接口的实例，或者复写了Thread的run方法，会执行该方法的逻辑单元（逻辑代码）
 * 3.如果构造线程对象时未传入ThreadGroup，Thread会默认获取父线程的ThreadGroup作为该线程方法的ThreadGroup，
 *   此时子线程和父线程将会在同一个ThreadGroup中
 * 4.构造Thread的时候传入stackSize代表该线程占用的stack大小，
 *   如果没有指定stackSize的大小，默认为0，0代表会忽略该参数，
 *   该参数会被JNI函数去使用
 *   需要注意：该参数有些平台有效，有些平台无效
 *   -Xss10M
 */
public class CreateThread1 {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
//        System.out.println(t.getThreadGroup().getName());
//
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup().getName());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.activeCount());

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
//        for (Thread temp:threads) {
//            System.out.println(temp);
//        }

        Arrays.asList(threads).forEach(System.out::println);

    }
}