package per.study.thread.base.chapter6;

public class ThreadInterrupt2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
            }
        });
        t1.start();

        Thread main_thread = Thread.currentThread();
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            main_thread.interrupt();
            System.out.println("interrupt");
        });

        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
