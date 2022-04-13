package per.study.juc.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {

    @Test
    public void test() {
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList();

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");

        System.out.println(arrayList);

        arrayList.removeAll(arrayList);
        System.out.println(arrayList);
    }

    @Test
    public void test1() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("3");
        arrayList.add("1");
        arrayList.add("2");
        System.out.println(arrayList);
        arrayList.remove("1");
        System.out.println(arrayList);
    }


}
