package per.study.feature.functional;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用
 * 数组引用
 */
public class ConstructReTest {
    /**
     * 构造器引用
     */
    @Test
    public void test1() {
        Supplier<Person> sp1 = () -> new Person();
        sp1.get();

        Supplier<Person> sp2 = Person::new;
        sp2.get();

        Function<String, Person> sp3 = name -> new Person(name);
        sp3.apply("Tom");

        Function<String, Person> sp4 = Person::new;
        sp4.apply("Jerry");

        BiFunction<String, Integer, Person> fun1 = (name, id) -> new Person(name, id);
        fun1.apply("Tom", 101);

        BiFunction<String, Integer, Person> fun2 = Person::new;
        fun2.apply("Jerry", 102);
    }

    @Test
    public void test2() {
        Function<Integer, String[]> func1 = length -> new String[length];
        String[] apply1 = func1.apply(10);
        System.out.println(Arrays.toString(apply1));

        Function<Integer, String[]> func2 = String[]::new;
        String[] apply2 = func2.apply(10);
        System.out.println(Arrays.toString(apply2));

    }

}
