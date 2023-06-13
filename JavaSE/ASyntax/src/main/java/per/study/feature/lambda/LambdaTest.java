package per.study.feature.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式的写法
 * 1、格式：
 *      ->    ：Lambda操作符
 *      ->左边：Lambda形参列表（接口中的抽象方法的形参列表）
 *      ->右边：Lambda体（重写的抽象方法的方法体）
 *
 * 2、使用：
 *  1、无参、无返回值
 *  2、需要一个参数，但是没有返回值
 *  3、数据类型可以省略；因为可由编译器推断得出，成为"类型推断"
 *  4、Lambda若只需要一个参数时，参数的小括号可以省略
 *  5、需要两个或以上的参数，多条执行语句，并且可以有返回值
 *  6、若Lambda体只有一条语句时，return和大括号若有，都可以省略
 *
 * 3、本质：作为接口的实例
 *
 * 4、总结
 *  ->左边：Lambda形参列表的参数类型可以省略（类型推断）；如果Lambda形参列表只有一个参数，其一对()也可以省略
 *  ->右边：Lambda体应该使用一对{}包裹；如果Lambda体只有一条执行语句（肯能是return语句），可以省略一对{}和return关键字
 */
public class LambdaTest {

    /**
     * 无参、无返回值
     */
    @Test
    public void test1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        runnable.run();
        Runnable r = () -> System.out.println("world");
        r.run();
    }

    /**
     * 需要一个参数，但是没有返回值
     */
    @Test
    public void test2() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("hello, world!");

        Consumer<String> con = (String s) -> {
            System.out.println(s);
        };
        con.accept("new world!");
    }

    /**
     * 数据类型可以省略；因为可由编译器推断得出，成为"类型推断"
     */
    @Test
    public void test3() {
        Consumer<String> con = (s) -> { System.out.println(s); };
        con.accept("new world!");
    }

    /**
     * Lambda若只需要一个参数时，参数的小括号可以省略
     */
    @Test
    public void test4() {
        Consumer<String> con = s -> { System.out.println(s); };
        con.accept("new world!");
    }

    /**
     * 需要两个或以上的参数，多条执行语句，并且可以有返回值
     */
    @Test
    public void test5() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare1 = com1.compare(1, 2);
        System.out.println(compare1);
        System.out.println("----------------");

        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1, o2);
        };
//        Comparator<Integer> com2 = Comparator.comparingInt(o -> o);
        int compare2 = com2.compare(2, 1);
        System.out.println(compare2);
        System.out.println("----------------");

        // 方法饮用
        Comparator<Integer> com3 = Integer :: compare;
        int compare3 = com3.compare(1, 0);
        System.out.println(compare3);
    }

    /**
     * 若Lambda体只有一条语句时，return和大括号若有，都可以省略
     */
    @Test
    public void test6() {
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(com2.compare(1, 2));
    }

}
