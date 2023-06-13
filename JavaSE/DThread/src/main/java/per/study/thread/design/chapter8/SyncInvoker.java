package per.study.thread.design.chapter8;

/**
 * Future         -> 代表的是未来的一个凭据
 * FutureTask     -> 将你的调用逻辑进行了隔离
 * FutureService  -> 桥接Future和FutureTask
 */
public class SyncInvoker {
    public static void main(String[] args) throws InterruptedException {
        /*String result = get();
        System.out.println(result);*/
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, System.out::println);

        System.out.println("============");
        System.out.println("do other things....");
        Thread.sleep(1000L);
        System.out.println("============");
//        System.out.println(future.get());

    }

    private static String get() throws InterruptedException {
        Thread.sleep(10_000L);
        return "FINISH";
    }
}
