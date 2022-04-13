package per.study.juc.notify.time;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new Date());
//                timer.cancel();
            }
        }, 1000, 2000);
    }
}
