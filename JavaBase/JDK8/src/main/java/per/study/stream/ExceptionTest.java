package per.study.stream;

import java.util.ArrayList;
import java.util.List;

public class ExceptionTest {

    private static final Boolean isClosed = new Boolean(true);

    public static void test() {
        if (isClosed) {
            throw new RuntimeException("he");
        }

        System.out.println("test");
    }

    public static void main(String[] args) {
//        test();
//        System.out.println("yyy");

        List<String> u = new ArrayList<String>();
        if (!u.contains("e")) {
            u.add("e");
        }
        u.add("t");

//        u.add("e");
//        u.add("b");
//        if (!u.contains("e")) {
//            u.add("e");
//        }
//
//        u.add("f");

    }

}
