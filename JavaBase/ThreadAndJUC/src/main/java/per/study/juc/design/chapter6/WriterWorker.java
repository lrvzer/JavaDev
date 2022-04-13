package per.study.juc.design.chapter6;

import java.util.Random;

public class WriterWorker extends Thread{

    private final Random random = new Random(System.currentTimeMillis());

    /*共享数据*/
    private final SharedData data;

    /*填充数据*/
    private final String filler;

    private int index;

    public WriterWorker(SharedData data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index ++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }

}
