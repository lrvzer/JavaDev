package per.study.dp.latch;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ProgrammerTravel extends Thread {

    private final Latch latch;
    private final String programmer;
    private final String transportation;

    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer + " start take the transportation[" + transportation + "]");
        try {
            // 程序员乘坐交通工具随机消费时间
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(programmer + " arrived by transportation[" + transportation + "]");
        // 完成任务时使计数器减一
        this.latch.countDown();
    }
}
