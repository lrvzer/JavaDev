package per.study.juc.executor;

import java.util.Timer;
import java.util.TimerTask;

public class TimerScheduler {

    /**
     * 定时任务
     * Scheduler solution
     *      Timer / TimerTask
     *      SchedulerExecutorService
     *      crontab
     *      cron4j
     *      quartz
     * @param args
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        final TimerTask  task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("=======" + System.currentTimeMillis());
            }
        };
        timer.schedule(task, 1000, 1000);
    }

}
