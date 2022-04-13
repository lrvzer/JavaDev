package per.study.domain;

import java.util.Date;

public class Person1
{

    private String name;
    private Integer age;
    private Date birthday;

    public Person1(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }



    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + ", birthday=" + birthday + '}';
    }
}
