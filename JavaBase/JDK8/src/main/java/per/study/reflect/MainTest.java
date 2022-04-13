package per.study.reflect;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.*;
import java.util.Properties;
import java.util.Random;

/**
 * 关于java.lang.Class类的理解
 * 1.类的加载过程：
 *  程序经过javac.exe命令后，会生成一个或多个字节码文件（.class），
 *  接着使用java.exe命令对某个字节码文件进行解释运行，相当于将某个字节码文件加载到内存中，此过程就称为类的加载。
 *  加载到内存中的类，我们称为运行时类，此运行时类，就作为Class的一个实例。
 * 2.Class的实例就对应着一个运行时类
 * 3.加载到内存中的运行时类，会缓存一定的时间，在此时间之内，我们可以通过不同的方式来获取此运行时类
 */
public class MainTest {

    /**
     * 反射之前，对于Person的操作
     *   在Person类外部，不可以通过Person类的对象调用其内部私有结构
     *   比如：name、showNation()以及私有的构造器
     */
    @Test
    public void test1() {
        // 1、创建Person类的对象
        Person p = new Person("Tom", 29);

        // 2、通过对象，调用其内部属性、方法
        p.age = 10;
        p.show();
        System.out.println(p);
    }

    /**
     * 反射之后，对于Person的操作
     */
    @Test
    public void test2() throws Exception {
        Class clazz = Person.class;
        // 1、通过反射创建Person类的对象
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        Object tom = constructor.newInstance("Tom", 12);
        System.out.println(tom);

        // 2、通过反射调用对象指定的属性、方法（公有）
        Field age = clazz.getField("age");
        age.set(tom, 10);
        System.out.println(tom);

        Method show = clazz.getDeclaredMethod("show");
        show.invoke(tom);

        // 3、通过反射调用Person类的私有结构
        // 私有构造器
        Constructor cons = clazz.getDeclaredConstructor(String.class);
        cons.setAccessible(true);
        Object jerry = cons.newInstance("Jerry");
        System.out.println(jerry);

        // 私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(jerry, "hello");
        System.out.println(jerry);

        // 私有方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String chinese = (String) showNation.invoke(jerry, "Chinese");
        System.out.println(chinese);
    }

    /**
     * 获取Class的实例的方式
     *      调用运行时类的属性：.class
     *      调用运行时类的对象，调用getClass()方法
     *      调用Class的静态方法：forName(String className)
     *      使用类的加载器：ClassLoader
     */
    @Test
    public void test3() {
        // 方式一：调用运行时类的属性：.class
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);

        // 方式二：调用运行时类的对象，调用getClass()方法
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);

        // 方式三：调用Class的静态方法：forName(String className)
        Class clazz3 = null;
        try {
            clazz3 = Class.forName("senior.reflect.Person");
            System.out.println(clazz3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);

        // 方式四：使用类的加载器：ClassLoader
        Class clazz4 = null;
        ClassLoader classLoader = MainTest.class.getClassLoader();
        try {
            clazz4 = classLoader.loadClass("senior.reflect.Person");
            System.out.println(clazz4);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class实例可以是以下结构：
     *      类、接口、数组、数组、enum、annotation、基本数据类型、void、Class
     */
    @Test
    public void test4() {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[10];
        Class aClass = a.getClass();
        Class bClass = b.getClass();
        // 只要元素数据类型与维度一样，就是同一个Class
        System.out.println(aClass == bClass); // true
    }

    @Test
    public void showClassLoader() {
        // 对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = MainTest.class.getClassLoader();
        System.out.println(classLoader); // sun.misc.Launcher$AppClassLoader@18b4aac2

        // 调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent); // sun.misc.Launcher$ExtClassLoader@5305068a

        // 调用扩展类加载器的getParent()：无法获取引导类加载器
        // 引导类加载器主要负责加载java的核心类库，无法加载自定义类
        ClassLoader pP = parent.getParent();
        System.out.println(pP); // null

        ClassLoader s = String.class.getClassLoader();
        System.out.println(s); // null
    }

    /**
     * 获取配置文件信息
     *  Properties
     *  ClassLoader
     * @throws Exception
     */
    @Test
    public void getPropertiesInfo() throws Exception{
        // 配置文件默认位于当前module下
        Properties p1 = new Properties();
        FileInputStream fis = new FileInputStream("jdbc.properties");
        p1.load(fis);
        String name = p1.getProperty("name");
        System.out.println(name);
        fis.close();

        // 配置文件默认位于当前module的src下
        Properties p2 = new Properties();
        ClassLoader classLoader = MainTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
        p2.load(is);
        String pwd = p2.getProperty("pwd");
        System.out.println(pwd);
    }

    /**
     * 通过反射创建运行时类的对象
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void test7() throws InstantiationException, IllegalAccessException {
        Class clazz = Person.class;
        /**
         * newInstance()：调用此方法，创建对应的运行时类的对象；内部默认调用了运行时类的空参构造器
         *
         * 要想此方法正常的创建运行时类的对象，要求：
         * 1、运行时类必须提供空参构造器
         * 2、空参构造器的访问权限通常设置为public
         *
         * 在javabean中要求提供一个public的空参构造器，原因：
         * 1、便于通过反射，创建运行时类的对象
         * 2、便于子类继承此运行类时，默认调用super()时，保证弗雷由此构造器
         */
        Object obj = clazz.newInstance();
        System.out.println(obj);
    }

    /**
     * 反射的动态性
     * @throws Exception
     */
    @Test
    public void reflectDynamics() throws Exception {
        int num = new Random().nextInt(3); // 0 1 2
        String classPath = "";
        switch (num) {
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.Object";
                break;
            case 2:
                classPath = "senior.reflect.Person";
                break;
            default:
                break;
        }
        Object instance = getInstance(classPath);
        System.out.println(instance);
    }

    /**
     * <pre>创建一个指定类的对象</pre>
     * @param classPath 指定类的全类名
     * @return
     * @throws Exception
     */
    public Object getInstance(String classPath) throws Exception {
        Class aClass = Class.forName(classPath);
        return aClass.newInstance();
    }

    /**
     * 获取属性结构
     * getFields()：获取当前运行时类及其父类中声明为public访问权限的属性
     * getDeclaredFields()：获取当前运行时类中声明的所有属性（不包含父类中声明的属性）
     */
    @Test
    public void getFieldStruct() {
        Class clazz = NewPerson.class;
        Field[] fields = clazz.getFields();
        for (Field field:fields) {
            System.out.println(field);
        }

        System.out.println("===================");

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field:declaredFields) {
            // 权限修饰符
            int modifier = field.getModifiers();
            // 数据类型
            Class type = field.getType();
            // 变量名
            String name = field.getName();
            System.out.println(field + "-->" + Modifier.toString(modifier) + "-->" + type.getName() + "-->" + name);
        }
    }

    /**
     * 获取运行时类的方法结构
     * getMethods()：获取当前运行时类及其左右父类中声明为public权限的方法
     * getDeclaredMethods()：获取当前运行时类中声明的所有方法（不包含父类中声明的方法）
     */
    @Test
    public void getMethodStruct() {
        Class clazz = NewPerson.class;
        Method[] methods = clazz.getMethods();
        for (Method m:methods) {
            System.out.println(m);
        }
        System.out.println("====================");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m:declaredMethods) {
            System.out.println("-------------------");
            // 1.获取注解
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a:annotations) {
                System.out.println(a);
            }
            // 2.权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");
            // 3.返回值类型
            System.out.print(m.getReturnType().getName() + "\t");
            // 4.方法名
            System.out.print(m.getName() + "(");
            // 5.获取参数类型
            Class[] parameterTypes = m.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0)) {
                for (int i=0; i<parameterTypes.length; i++) {
                    if (i == parameterTypes.length - 1) {
                        System.out.print(parameterTypes[i].getName() + "_args_" + i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName() + "args_" + i + ", ");
                }
            }
            System.out.print(")");

            // 6.获取异常
            Class[] exceptionTypes = m.getExceptionTypes();
            if ( exceptionTypes.length > 0) {
                System.out.print(" throws ");
                for (int i=0; i<exceptionTypes.length; i++) {
                    if (i == exceptionTypes.length - 1) {
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ", ");
                }
            }
            System.out.println();
//            System.out.println(m);
        }
    }

    /**
     * 获取运行时类的构造器结构
     * getConstructors()：获取当前运行时类中声明为public的构造器
     * getDeclaredConstructors()：获取当前运行时类中声明的所有构造器
     */
    @Test
    public void getConstructorStruct() {
        Class clazz = NewPerson.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c:constructors) {
            System.out.println(c);
        }
        System.out.println("===========================");
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor c:declaredConstructors) {
            System.out.println(c);
        }
    }

    /**
     * 获取运行时类的父类
     * 获取运行时类带范型的父类
     * 获取运行时类带范型的父类的范型
     */
    @Test
    public void getParentClass() {
        Class clazz = NewPerson.class;
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);

        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);


        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(actualTypeArguments[0].getTypeName());
    }

    /**
     * 获取运行时类实现的接口
     * 获取运行时类所在的包
     */
    @Test
    public void getOtherStruct() {
        /// 获取运行时类实现的接口 ///
        Class clazz = NewPerson.class;
        Class[] interfaces = clazz.getInterfaces();
        for (Class i:interfaces) {
            System.out.println(i);
        }

        System.out.println();
        Class superclass = clazz.getSuperclass();
        // 获取运行时类的父类实现的接口
        Class[] superclassInterfaces = superclass.getInterfaces();
        for (Class i:superclassInterfaces) {
            System.out.println(i);
        }
        /// 获取运行时类所在的包 ///
        Package aPackage = clazz.getPackage();
        System.out.println(aPackage);
        System.out.println();

        /// 获取运行时类声明的注解 ///
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation a:annotations) {
            System.out.println(a);
        }
    }

    /**
     *
     */
    @Test
    public void getAppointPublicField() throws Exception {
        Class<NewPerson> clazz = NewPerson.class;
        // 创建运行时类对象
        NewPerson p = clazz.newInstance();
        // 获取指定的属性getField()：要求运行时类中属性声明为public
        Field id = clazz.getField("id");
        /**
         * 设置当前属性的值
         * set(Object obj, Object value)：被设置的对象obj，设置的属性值value
         */
        id.set(p, 1001);

        /**
         * 获取当前属性的值
         * get(Object obj)：获取对象obj的属性值
         */
        Object o = id.get(p);
        System.out.println(o);
    }

    @Test
    public void getAppointAnyField() throws Exception {
        Class<NewPerson> clazz = NewPerson.class;
        // 创建运行时类对象
        NewPerson p = clazz.newInstance();
        // 获取指定的属性getDeclaredField()：获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");
        // 保证当前属性是可访问的
        name.setAccessible(true);
        name.set(p, "Tom");
        System.out.println(name.get(p));
    }

    @Test
    public void getAppointAnyMethod() throws Exception {
        Class<NewPerson> clazz = NewPerson.class;
        // 创建运行时类的对象
        NewPerson newPerson = clazz.newInstance();

        /**
         * 获取指定的某个方法
         * getDeclaredMethod(String name, Class<?>... parameterTypes)
         *      name：方法名
         *      parameterTypes：形参列表
         */
        Method show = clazz.getDeclaredMethod("show", String.class);
        // 设置方法可访问
        show.setAccessible(true);

        /*
         * Object invoke(Object obj, Object... args)
         *      obj：方法的调用者
         *      args：形参赋值的实参
         *      return：返回值Object
         */
        show.invoke(newPerson, "Chinese");

        System.out.println("调用静态方法");
        Method showInfo = clazz.getDeclaredMethod("showInfo");
        showInfo.setAccessible(true);
        showInfo.invoke(NewPerson.class);
        showInfo.invoke(null);
    }

    @Test
    public void getAppointConstructor() throws Exception {
        Class<NewPerson> clazz = NewPerson.class;
        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        NewPerson tom = (NewPerson) constructor.newInstance("Tom");
        System.out.println(tom);
    }

}
