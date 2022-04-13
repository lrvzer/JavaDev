package per.study.juc.thread.chapter11;

public class ExitCapture {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The application will be exit.");
            notifyAndRelease();
        }));

        int i = 0;
        while (true) {
            try {
                Thread.sleep(1_000L);
                System.out.println("I am working.");
            } catch (InterruptedException e) {
                // ignore
            }
            i++;
            if (i > 20) throw new RuntimeException("error");
        }
    }

    private static void notifyAndRelease() {
        System.out.println("notify to the admin");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("will release resource(socket file connection)");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("notify done");
    }
}
