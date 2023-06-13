package per.study.feature.functional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Java内置的4大核心函数式接口
 *
 * 消费型接口 Consumer<T>      void accept(T t)
 * 供给型接口 Supplier<T>      T get()
 * 函数型接口 Function <T, R>  R apply(T t)
 * 断定型接口 Predicate<T>     boolean test(T t)
 */
public class FunctionalTest {

    public void happyTime(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void test1() {
        happyTime(500, money -> {
            System.out.println("消费了" + money + "元");
        });
    }

    public List<String> filterString(List<String> list, Predicate<String> pre) {
        ArrayList<String> filterList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)) {
                filterList.add(s);
            }
        }
        return filterList;
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("北京", "南京", "天津", "东京", "西京", "普京");
        List<String> filterList = filterString(list, s -> s.contains("京"));
        System.out.println(filterList);
    }

}
