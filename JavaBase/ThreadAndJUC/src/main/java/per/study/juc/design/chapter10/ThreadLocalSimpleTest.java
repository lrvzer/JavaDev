package per.study.juc.design.chapter10;

public class ThreadLocalSimpleTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "Alex";
        }
    };

    public static void main(String[] args) throws InterruptedException {
//        threadLocal.set("Alex");

        Thread.sleep(1000);
        String value = threadLocal.get();
        System.out.println(value);
    }
}
