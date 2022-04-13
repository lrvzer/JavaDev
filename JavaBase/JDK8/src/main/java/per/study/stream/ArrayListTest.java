package per.study.stream;

import java.util.ArrayList;
import java.util.Objects;

public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        long count = list.stream().count();
        System.out.println(count);

        list.stream().filter(s -> !Objects.equals(s, "1")).forEach(System.out::println);
    }

}
