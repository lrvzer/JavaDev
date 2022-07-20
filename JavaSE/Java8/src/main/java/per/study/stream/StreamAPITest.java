package per.study.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1.
 *   Stream关注的是对数据的运算，与CPU打交道；
 *   集合关注的是数据的存储，与内存打交道
 *
 * 2.
 *   Stream不会存储元素；
 *   Stream不会改变源对象，相反，会返回一个持有结果的新Stream
 *   Stream操作是延迟执行的，这意味着他们会等到需要结果的时候才执行
 *
 * 3. Stream执行流程
 *   1) Steam的实例化
 *   2) 一系列的中间操作（过滤、映射、...）
 *      一个中间操作链，对数据源的数据进行处理
 *      (1) 筛选与切片
 *          Stream<T> filter(Predicate<? super T> predicate)：接收Lambda，从流中排除某些数据
 *          Stream<T> limit(long maxSize)：截断流，使其元素不超过给定数量
 *          Stream<T> skip(long n)：跳过元素，返回一个扔掉前n个元素的流，若流中元素中不足n个，则返回一个空流；与limit(n)互补
 *          Stream<T> distinct()：筛选，通过流所生成元素的hashCode()和equals()去除重复元素
 *
 *      (2) 映射
 *          Stream<R> map(Function<? super T, ? extends R> mapper)：接收一个函数作为参数，该函数会被应用到每个元素上，并映射成一个新的元素
 *          DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream
 *          IntStream mapToInt(ToIntFunction<? super T> mapper)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的IntStream
 *          LongStream mapToLong(ToLongFunction<? super T> mapper)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的LongStream
 *          Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
 *          ...
 *
 *      (3) 排序
 *          Stream<T> sorted()：自然排序
 *          Stream<T> sorted(Comparator<? super T> comparator)：定制排序
 *
 *   3) 终止操作（终端操作）
 *      一旦终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 *      (1) 匹配与查找
 *          boolean allMatch(Predicate<? super T> predicate)：检查是否匹配所有元素
 *          boolean anyMatch(Predicate<? super T> predicate)：检查是否至少匹配一个元素
 *          boolean noneMatch(Predicate<? super T> predicate)：检查是否没有匹配的元素
 *          Optional<T> findFirst()：返回第一个元素
 *          Optional<T> findAny()：返回当前流中的任意元素
 *          long count()：返回流中的任意元素
 *          Optional<T> max(Comparator<? super T> comparator)：返回流中的最大值
 *          Optional<T> min(Comparator<? super T> comparator)：返回流中最小值
 *          void forEach(Consumer<? super T> action)：内部迭代
 *
 *      (2) 归约
 *      T reduce(T identity, BinaryOperator<T> accumulator)：identity初始值，可以将流中元素反复结合起来，得到一个值，返回T
 *      Optional<T> reduce(BinaryOperator<T> accumulator)：可以将流中元素反复结合起来，得到一个值，返回Optional<T>
 *
 *      (3) 收集
 *      <R, A> R collect(Collector<? super T, A, R> collector)：将流转换为其他形式
 */
public class StreamAPITest {

    @Test
    public void testl() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        List<Integer> collect = integers.stream().filter(i -> i>5).collect(Collectors.toList());
        Set<Integer> collect = integers.stream().filter(i -> i>5).collect(Collectors.toSet());
        System.out.println(collect);
    }

    @Test
    public void test12() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = integers.stream().reduce(10, Integer::sum);
        System.out.println(reduce);
    }

    @Test
    public void test11() {
        List<Integer> integers = Arrays.asList(12, 34, 55, 2, 21, 18);
//        boolean b = integers.stream().allMatch(i -> i > 1);
//        boolean b = integers.stream().anyMatch(i -> i > 50);
//        boolean b = integers.stream().noneMatch(i -> i == 5);
//        Optional<Integer> first = integers.stream().findFirst();
//        Optional<Integer> first = integers.parallelStream().findAny();
//        long first = integers.parallelStream().count();
//        Optional<Integer> first = integers.parallelStream().max(Integer::compare);
//        Optional<Integer> first = integers.parallelStream().min(Integer::compare);
        integers.parallelStream().forEach(System.out::println);
    }

    @Test
    public void test() {
        List<Integer> integers = Arrays.asList(12, 34, 55, 2, 21, 18);
        integers.stream().sorted().forEach(System.out::println);
    }

    @Test
    public void test0() {
        List<String> strings = Arrays.asList("aa", "bb", "ccc");
//        strings.stream().map(s -> s.toUpperCase()).filter(s -> s.length() > 2).forEach(System.out::println);
        Stream<Stream<Character>> streamStream = strings.stream().map(StreamAPITest::formatCharacter);
        streamStream.forEach( characterStream -> characterStream.forEach(System.out::println));

        strings.stream().flatMap(StreamAPITest::formatCharacter).forEach(System.out::println);
    }

    public static Stream<Character> formatCharacter(String str) {
        ArrayList<Character> objects = new ArrayList<>();
        for (Character s : str.toCharArray()) {
            objects.add(s);
        }
        return objects.stream();
    }


    /**
     * 创建Stream方式一：通过集合
     */
    @Test
    public void test1() {
        List<String> stringList = new ArrayList<>();
        stringList.add("hello");
        stringList.add("new");
        stringList.add("world");
        stringList.add("world");

        // default Stream<E> stream()：返回一个顺序流
        Stream<String> stream = stringList.stream();

        // 查询字符串中包含'l'的字符串
//        stream.filter(t -> t.contains("l")).forEach(System.out::println);

        //
//        stream.limit(2).forEach(System.out::println);

//        stream.skip(2).forEach(System.out::println);
        stream.distinct().forEach(System.out::println);

        // default Stream<E> parallelStream()：返回一个并行流
        Stream<String> stringStream = stringList.parallelStream();
    }

    /**
     * 创建Stream方式二：通过数组
     */
    @Test
    public void test2() {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        // <T> Stream<T> stream(T[] array)
        IntStream stream = Arrays.stream(arr);
    }

    /**
     * 创建Stream方式三：通过Stream的of()
     */
    @Test
    public void test3() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
    }

    /**
     * 创建Stream方式四：创建无限流
     */
    @Test
    public void test4() {
        // 遍历前10个偶数
        // Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        Stream.iterate(0, t -> t+2).limit(10).forEach(System.out::println);

        // Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }




}
