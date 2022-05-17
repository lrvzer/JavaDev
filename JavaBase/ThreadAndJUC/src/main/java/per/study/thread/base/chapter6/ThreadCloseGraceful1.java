package per.study.thread.base.chapter6;

public class ThreadCloseGraceful1 {

    private static class Worker extends Thread {
        private volatile boolean terminate = true;

        @Override
        public void run() {
            while (terminate) {
                //TODO
            }
        }

        public void shutdown() {
            this.terminate = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.shutdown();
    }

}
