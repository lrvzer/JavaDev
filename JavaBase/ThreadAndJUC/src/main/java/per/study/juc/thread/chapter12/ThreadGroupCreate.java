package per.study.juc.thread.chapter12;

public class ThreadGroupCreate {
    public static void main(String[] args) {
        // use the name
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, () -> {
            try {
                System.out.println(Thread.currentThread().getThreadGroup().getName());
                System.out.println(Thread.currentThread().getThreadGroup().getParent());
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1, "tg2");
        Thread t2 = new Thread(tg2, () -> {
            try {
                System.out.println(Thread.currentThread().getThreadGroup().getName());
                System.out.println(Thread.currentThread().getThreadGroup().getParent());
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t2.start();

        // use the parent and group name

//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup().getName());
    }
}
