package per.study.io;

import java.io.Serializable;

/**
 * Person类可序列化，需满足以下条件
 * 1、需要实现接口：Serializable
 * 2、当前类提供一个全局常量：serialVersionUID
 * 3、除了当前Person类需要实现接口之外，还必须保证其内部所有与属性也必须是可序列化的
 *    （默认情况下，基本数据类型可序列化）
 * 否则抛出异常：NotSerializableException
 */
public class Person implements Serializable
{

    public static final long serialVersionUID = 3232322332323555L;

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
