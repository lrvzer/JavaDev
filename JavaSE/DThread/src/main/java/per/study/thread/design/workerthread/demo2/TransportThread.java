package per.study.thread.design.workerthread.demo2;

import java.util.Random;

public class TransportThread extends Thread {

    private final Channel channel;

    private static final Random RANDOM = new Random(System.currentTimeMillis());


    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                this.channel.offer(request);
                Thread.sleep(RANDOM.nextInt(1_000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
