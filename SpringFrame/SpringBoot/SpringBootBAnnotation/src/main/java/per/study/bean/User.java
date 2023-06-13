package per.study.bean;

public class User {

    private String name;
    private Integer age;
    private Cat cat;

    public Cat getPet() {
        return cat;
    }

    public void setPet(Cat cat) {
        this.cat = cat;
    }

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + "', age=" + age + '}';
    }
}
