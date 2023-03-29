package per.study.thread.base.chapter6;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt3 {
    public static void main(String[] args) {
        //① 判断当前线程是否被中断
        System.out.println("Main thread is interrupted? " + Thread.interrupted());

        //② 中断当前线程
        Thread.currentThread().interrupt();

        //③ 判断当前线程是否已经被中断
        System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());

        try {
            //④ 当前线程执行可中断方法
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            //⑤ 捕获中断信号
            System.out.println("I will be interrupted still.");
        }
    }
}
