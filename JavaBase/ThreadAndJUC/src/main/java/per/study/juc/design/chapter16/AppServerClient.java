package per.study.juc.design.chapter16;

import java.io.IOException;

public class AppServerClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer appServer = new AppServer(13345);
        appServer.start();

        Thread.sleep(5_000L);
        appServer.shutdown();
    }
}
