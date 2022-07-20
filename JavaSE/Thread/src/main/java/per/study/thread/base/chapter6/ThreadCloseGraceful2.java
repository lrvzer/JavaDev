package per.study.thread.base.chapter6;

public class ThreadCloseGraceful2 {

    private static class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
                /*try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    break; // return
                }*/

                if (Thread.interrupted()) {
                    break;
                }
            }
            // -----------用return，后面的执行不到
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
        worker.interrupt();
    }

}
