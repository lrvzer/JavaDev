package per.study.guava.utils;

import java.util.Arrays;
import java.util.List;

public class JoinerTest {

    private static final List<String> stringList = Arrays.asList("Google", "Guava", "Java", "Scale", "Kafka");

    private static final List<String> stringListWithNullValue = Arrays.asList("Google", "Guava", "Java", "Scale", null);

    public void doSom() {
        try {

        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public static void main(String[] args) {
//        System.out.println(Joiner.on(";").join(stringList));
//        System.out.println(Joiner.on(";").skipNulls().join(stringListWithNullValue));
//        System.out.println(Joiner.on(";").useForNull("DEFAULT").join(stringListWithNullValue));

//        String s = UUID.randomUUID().toString();
//        System.out.println(s);
//        int i = Integer.parseInt(s);
//        System.out.println(i);
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 100;
        Integer i4 = 100;

        System.out.println((i1==i2));
        System.out.println((i3==i4));
    }
}
