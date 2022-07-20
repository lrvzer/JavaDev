package per.study.thread.base.chapter11;

public class ThreadException {

    /*private static final int A = 10;
    private static final int B = 0;*/
    public static void main(String[] args) {
        new Test1().test();
        /*Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5_000L);
                int result = A / B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 捕获线程异常
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(e);
            System.out.println(t);
        });
        thread.start();*/
    }
}
