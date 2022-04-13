package per.study.juc.design.chapter7;

/**
 * 不可变对象一定是线程安全的，里面的任何属性或者或者饮用类型的属性都不能被修改
 * 可变对象不一定是不安全的 StringBuffer
 */
public final class Person {

    private final String name;
    private final String address;

    public Person(final String name, final String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
