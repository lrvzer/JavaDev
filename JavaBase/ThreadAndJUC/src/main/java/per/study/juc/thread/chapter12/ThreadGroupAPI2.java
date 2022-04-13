package per.study.juc.thread.chapter12;

public class ThreadGroupAPI2 {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, () -> {
            try {
                Thread.sleep(1_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

//        tg1.setDaemon(true);

        t1.start();
        Thread.sleep(2_000);
        System.out.println(tg1.isDestroyed());
        tg1.destroy();
        System.out.println(tg1.isDestroyed());


    }
}
