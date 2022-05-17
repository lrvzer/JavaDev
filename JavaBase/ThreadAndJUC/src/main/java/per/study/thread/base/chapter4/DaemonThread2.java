package per.study.thread.base.chapter4;

public class DaemonThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(()-> {
            Thread innerThread = new Thread(() -> {
                try {
                    while (true) {
                        System.out.println("Do something for health check");
                        Thread.sleep(1_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            innerThread.setDaemon(true);
            innerThread.start();

            try {
                Thread.sleep(1_000);
                System.out.println("T thread finish done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}
