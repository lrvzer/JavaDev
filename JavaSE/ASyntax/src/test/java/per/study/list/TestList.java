package per.study.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TestList {

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.removeIf(s -> s=="1");
        System.out.println(list);
    }

}
