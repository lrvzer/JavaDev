package per.study.thread.base.chapter6;

public class ThreadInterrupt1 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {

                // 打断方式二
                /*synchronized (MONITOR) {
                    try {
                        MONITOR.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.interrupted());
                    }
                }*/

                // 打断方式一
                /*try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("收到打断信号");
                    e.printStackTrace();
                }*/
            }
        });

        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
