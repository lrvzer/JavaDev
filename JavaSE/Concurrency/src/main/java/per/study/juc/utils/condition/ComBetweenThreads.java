package per.study.juc.utils.condition;

import java.util.concurrent.TimeUnit;

public class ComBetweenThreads {

    private static int data = 0;

    private static boolean noUse = true;

    private static final Object MONITOR = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            for (; ; )
                buildData();
        }).start();

        new Thread(() -> {
            for (; ; )
                useData();
        }).start();

    }

    private static void sleep(long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buildData() {
        synchronized (MONITOR) {
            while (noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            data++;
            sleep(1);
            System.out.println("P=>" + data);
            noUse = true;
            MONITOR.notifyAll();
        }
    }

    private static void useData() {
        synchronized (MONITOR) {
            while (!noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            sleep(1);
            System.out.println("C=>" + data);
            noUse = false;
            MONITOR.notifyAll();
        }
    }


}
