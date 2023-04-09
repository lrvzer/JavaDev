package per.study.guava.utils;

public class TT {


    public static void oper(StringBuffer x, StringBuffer y) {
        System.out.println("x<<:"+x);
        System.out.println("y<<:"+y);
        System.out.println("1x:"+x+"@"+Integer.toHexString(System.identityHashCode(x)));
        System.out.println("1y:"+y+"@"+Integer.toHexString(System.identityHashCode(y)));
        x.append(y);
        y = x;
        System.out.println("x>>:"+x);
        System.out.println("y>>:"+y);

        System.out.println("2x:"+x+"@"+Integer.toHexString(System.identityHashCode(x)));
        System.out.println("2y:"+y+"@"+Integer.toHexString(System.identityHashCode(y)));
    }

    public static void main(String[] args) {
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        System.out.println("a:"+a+"@"+Integer.toHexString(System.identityHashCode(a)));
        System.out.println("b:"+b+"@"+Integer.toHexString(System.identityHashCode(b)));
        oper(a, b);


        System.out.println("oa:"+a+"@"+Integer.toHexString(System.identityHashCode(a)));
        System.out.println("ob:"+b+"@"+Integer.toHexString(System.identityHashCode(b)));

        System.out.println(a + "" + b);

    }
}
