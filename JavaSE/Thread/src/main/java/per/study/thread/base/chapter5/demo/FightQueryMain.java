package per.study.thread.base.chapter5.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FightQueryMain {

    private static final List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        final List<String> result = search("SH", "BJ");
        System.out.println("============result============");
        result.forEach(System.out::println);
    }

    private static List<String> search(String original, String destination) {
        final List<String> result = new ArrayList<>();
        // 创建线程
        List<FightQueryTask> tasks = fightCompany
                .stream()
                .map(fight -> createSearchTask(fight, original, destination))
                .collect(Collectors.toList());

        // 启动线程
        tasks.forEach(Thread::start);

        // 调用join，阻塞当前线程
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 在此之前，当前线程会阻塞住，获取每一个查询线程的结果，并且加入到result中
        tasks.stream().map(FightQueryTask::get).forEach(result::addAll);
        return result;
    }

    private static FightQueryTask createSearchTask(String fight, String original, String destination) {
        return new FightQueryTask(fight, original, destination);
    }
}
