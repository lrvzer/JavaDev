package per.study.feature.functional;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用的使用
 * 1.使用情景：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用
 * 2.方法引用，本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例
 * 3.使用格式：类(或者对象)::方法名
 * 4.具体方法为如下的三种情况
 *      对象::非静态方法
 *      类::静态方法
 *      类::非静态方法
 * 5.方法引用的要求：要求接口中的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同
 */
public class MethodTest {

    /**
     * 对象::非静态方法
     * Consumer：void accept(T t);
     * PrintStream：void println(T t)
     */
    @Test
    public void test1() {
        // 1
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con1.accept("---1---");
        // 2
        Consumer<String> con2 = s -> System.out.println(s);
        con2.accept("---2---");

        // 3
        Consumer<String> con3 = System.out::println;
        con3.accept("---3---");
    }

    /**
     * Supplier<T>：T get()
     * Person：String getName()
     */
    @Test
    public void test2() {
        Person person = new Person("Tom");
        Supplier<String> supplier = person::getName;
        System.out.println(supplier.get());
    }

    /**
     * 类::静态方法
     * Comparator<T>：int compare(T o1, T o2)
     * Integer：int compare(int x, int y)
     */
    @Test
    public void test3() {
//        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(11, 22));
    }

    /**
     * Function<T, R>：R apply(T t)
     * Math：long round(double a)
     */
    @Test
    public void test4() {
        // 1
        Function<Double, Long> func1 = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        System.out.println(func1.apply(12.6));

        // 2
        Function<Double, Long> func2 = d -> Math.round(d);
        System.out.println(func2.apply(12.6));

        Function<Double, Long> func3 = Math::round;
        System.out.println(func3.apply(12.6));
    }

    /**
     * 类::实例方法
     * Comparator<T>：int compare(T o1, T o2)
     * String：int compareTo(String anotherString)
     */
    @Test
    public void test5() {
        Comparator<String> com1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com1.compare("hello", "hallo"));

        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("new", "naw"));
    }

    @Test
    public void test6() {
        BiPredicate<String, String> pre1 = (s1, s2) -> s1.equals(s2);
        System.out.println(pre1.test("abc", "abc"));

        BiPredicate<String, String> pre2 = String::equals;
        System.out.println(pre2.test("abc", "abd"));
    }

    @Test
    public void test7() {
        Function<Person, String> fun1 = p -> p.getName();
        System.out.println(fun1.apply(new Person("Tom")));

        Function<Person, String> fun2 = Person::getName;
        System.out.println(fun2.apply(new Person("Hr")));
    }
}
