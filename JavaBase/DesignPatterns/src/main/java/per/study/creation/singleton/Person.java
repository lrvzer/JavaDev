package per.study.creation.singleton;

/**
 * 使用场景
 * 多线程中的线程池
 * 数据库的连接池
 * 系统环境信息
 * 上下文（ServletContext）
 */
public class Person {

    private Person() {
        System.out.println("Create Person");
    }

    /**
     * 饿汉模式
     */
//    private final static Person instance = new Person();

    /**
     * 懒汉模式
     */
    private volatile static Person instance;

    /**
     * 提供外部方法
     * 1、public static synchronized Person getInstance()：锁太大
     * 2、双重检查锁+内存可见性(volatile)
     * @return singleton Person
     */
    public static Person getInstance() {
        if (instance == null) {
            synchronized (Person.class) {
                if (instance == null) {
                    instance = new Person();
                }
            }
        }
        return instance;
    }
}
