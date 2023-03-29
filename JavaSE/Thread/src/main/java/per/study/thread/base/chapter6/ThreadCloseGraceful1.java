package per.study.thread.base.chapter6;

/**
 * 使用volatile开关控制
 * <p>
 * 使用场景：
 * （1）线程的interrupt标识很有可能被擦除
 * （2）逻辑单元中不会调用任何可中断方法
 */
public class ThreadCloseGraceful1 {

    private static class Worker extends Thread {

        public Worker() {
            super();
        }

        public Worker(String name) {
            super(name);
        }

        private volatile boolean terminated = false;

        @Override
        public void run() {
            System.out.println("start-terminated flag: " + terminated + " interrupt flag: " + isInterrupted());
            while (!terminated && !isInterrupted()) {
                // TODO
                System.out.println("running-terminated flag: " + terminated + " interrupt flag: " + isInterrupted());
            }
            System.out.println("end-terminated flag: " + terminated + " interrupt flag: " + isInterrupted());
            System.out.println("end of run action.");
        }

        public void shutdown() {
            System.out.println("thread shutdown action.");
            this.terminated = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker("worker");
        worker.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.shutdown();
    }

}
