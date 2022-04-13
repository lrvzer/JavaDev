package per.study.juc.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OtherTest {

    @Test
    public void test() {
        List<String> list = getList();
        if (!list.isEmpty() && list.size() > 5) {
            System.out.println("5");
        } else {
            System.out.println("0");
        }
    }

    private List<String> getList() {
        List<String> stringList = new ArrayList<>();
        return stringList;
    }

}
