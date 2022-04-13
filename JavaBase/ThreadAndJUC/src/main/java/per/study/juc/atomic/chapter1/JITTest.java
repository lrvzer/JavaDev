package per.study.juc.atomic.chapter1;

public class JITTest {

    private static boolean init = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while(!init) {
//                System.out.println(".");
            }
            /**
             * while(true) {}
             * while(!init) {  System.out.println("."); }
             */
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            init = true;
            System.out.println("set init to true");
        }).start();
    }

}
