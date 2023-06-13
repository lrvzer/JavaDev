package per.study.thread.base.chapter12;

import java.util.Arrays;

public class ThreadGroupAPI1 {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, () -> {
            while (true) {
                try {
                    Thread.sleep(10_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        },"t1");
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1, "Tg2");
        Thread t2 = new Thread(tg2, () -> {
            while (true) {
                try {
                    Thread.sleep(10_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }, "t2");
        t2.start();

        System.out.println(tg1.activeCount());
        System.out.println(tg2.activeGroupCount());

        t2.checkAccess();
//        tg1.destroy();

        System.out.println("======================");
        Thread[] ts1 = new Thread[tg1.activeCount()];
        tg1.enumerate(ts1);
        Arrays.asList(ts1).stream().forEach(System.out::println);

        System.out.println("======================");
        tg1.enumerate(ts1, true);
        Arrays.asList(ts1).stream().forEach(System.out::println);

        System.out.println("======================");
        ts1 = new Thread[10];
        Thread.currentThread().getThreadGroup().enumerate(ts1, false);
        Arrays.asList(ts1).stream().forEach(System.out::println);

        tg1.interrupt();

    }
}
