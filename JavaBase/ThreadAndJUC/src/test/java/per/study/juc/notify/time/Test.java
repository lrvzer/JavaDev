package per.study.juc.notify.time;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        m(strings);
        System.out.println(strings);
    }

    public static void m(List<String> strings) {
        for (int i=0; i<10; i++) {
            strings.add("" + i);
        }
    }

}
