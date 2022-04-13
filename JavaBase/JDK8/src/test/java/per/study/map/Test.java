package per.study.map;

public class Test {

    public static void main(String[] args) {
        String a = "[ret:satisfy]";
        String[] split = a.split(";");
        System.out.println(a.substring(5,  a.indexOf("]")));
    }

}
