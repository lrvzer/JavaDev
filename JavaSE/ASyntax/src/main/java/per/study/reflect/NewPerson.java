package per.study.reflect;

@MyAnnotation(value = "hi")
public class NewPerson extends Creature<String> implements Comparable<String>, MyInterFace {

    private String name;
    int age;
    public int id;

    public NewPerson() {

    }

    @MyAnnotation(value = "abc")
    private NewPerson(String name) {
        this.name = name;
    }

    NewPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    private String show(String nation) {
        System.out.println("nation: " + nation);
        return nation;
    }

    public String display(String interests) throws RuntimeException, ClassCastException {
        return interests;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public void info() {
        System.out.println("我是一个人");
    }

    private static void showInfo() {
        System.out.println("new show info");
    }

    @Override
    public String toString() {
        return "NewPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
