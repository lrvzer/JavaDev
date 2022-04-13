package per.study.juc.design.chapter17;

import java.util.Random;

public class WorkerThread extends Thread {

    private final Channel channel;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            this.channel.take().execute();
            try {
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
