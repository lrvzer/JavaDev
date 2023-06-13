package per.study.thread.base.chapter4;

/**
 * 守护进程有时又被称为后台进程
 * 守护线程具备自动结束生命周期的特性
 * 使用场景：outerThread进程结束时，希望innerThread线程自动关闭
 */
public class DaemonThread2 {
    public static void main(String[] args) {
        Thread outerThread = new Thread(()-> {
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

            // outerThread进程结束时，innerThread线程会自动关闭
            innerThread.setDaemon(true);
            innerThread.start();

            try {
                Thread.sleep(1_000);
                System.out.println("T thread finish done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        outerThread.start();
    }
}
