package per.study.dp.future;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class FutureServiceTest {
    @Test
    public void testNoneReturnValue() throws InterruptedException {
        FutureService<Void, Void> service = FutureService.newService();
        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("I am finish done.");
        });

        future.get();
    }

    @Test
    public void testReturnValue() throws InterruptedException {
        FutureService<String, Integer> service = FutureService.newService();
        Future<Integer> future = service.submit(new Task<String, Integer>() {
            @Override
            public Integer get(String key) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return key.length();
            }
        }, "Hello");
        System.out.println(future.get());
    }

    @Test
    public void testCallBack() throws InterruptedException {
        FutureService<String, Integer> service = FutureService.newService();
        service.submit(key -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return key.length();
        }, "Hello", System.out::println);

        // 这里阻塞一下
        TimeUnit.SECONDS.sleep(15);
    }
}
