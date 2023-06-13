package per.study.feature.functional;

public class Person
{
    private String name;
    private Integer id;

    public Person() {
        System.out.println("空参构造器...");
    }

    public Person(String name) {
        this.name = name;
        System.out.println(name);
    }

    public Person(String name, Integer id) {
        this.name = name;
        this.id = id;
        System.out.println(name + "-" + id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
