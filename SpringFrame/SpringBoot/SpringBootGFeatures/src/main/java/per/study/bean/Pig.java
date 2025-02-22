package per.study.bean;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/4
 **/
//@ConfigurationProperties(prefix = "pig")
//@Component
public class Pig {
    private long id;
    private String name;

    private Integer age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "Pig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
