package per.study.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Bean工厂
 * Bean：计算机英语中，有可重用组件的含义
 */
public class BeanFactory {

    private static Properties prop;

    /* 定义一个Map，用于存放要创建的对象，称之为容器 */
    private static Map<String, Object> beans;

    static {
        try {
            prop = new Properties();
            InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("jdbc.properties");
            prop.load(is);
            /* 实例化容器 */
            beans = new HashMap<>();
            Enumeration keys = prop.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                String beanPath = prop.getProperty(key);
                Object value = Class.forName(beanPath).newInstance();
                beans.put(key, value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }

    /*public static Object getBean(String beanName) {
        Object bean = null;
        try {
            String beanPath = prop.getProperty(beanName);
            System.out.println(beanPath);
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }*/

}
