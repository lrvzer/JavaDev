package per.study.thread.base.chapter6;

/**
 * isInterrupted():测试线程是否已经中断，但是不能清除状态标识。
 */
public class ThreadInterrupt4 {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("线程启动了");
                while (!isInterrupted()) {
                    System.out.println(isInterrupted()); // after call interrupt(), isInterrupted() == true
                }
            }
        };

        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread.interrupt();
        System.out.println("线程是否被中断：" + thread.isInterrupted());
    }

}
