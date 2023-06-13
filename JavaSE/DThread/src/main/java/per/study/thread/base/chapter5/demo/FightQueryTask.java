package per.study.thread.base.chapter5.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FightQueryTask extends Thread implements FightQuery {

    // 出发地
    private final String origin;

    // 目的地
    private final String destination;

    private final List<String> fightList = new ArrayList<>();

    public FightQueryTask(String airline,String origin, String destination) {
        super("[" + airline + "]");
        this.origin = origin;
        this.destination = destination;
    }


    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s\n", getName(), origin, destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10); // 随机查询时间

        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.fightList.add(getName() + "-" + randomVal);
            System.out.printf("The fight: %s list query successful\n", getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取信息
     *
     * @return
     */
    @Override
    public List<String> get() {
        return this.fightList;
    }
}
