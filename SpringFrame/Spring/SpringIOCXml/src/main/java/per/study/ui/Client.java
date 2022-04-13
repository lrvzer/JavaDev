package per.study.ui;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import per.study.service.IAccountService;

public class Client {

    /**
     * 获取Spring的IOC核心容器，并根据id获取对象
     *
     * 1.ApplicationContext的三个常用实现类
     *      ClassPathXmlApplicationContext：可以加载类路径下的配置文件，要求配置文件必须在类路径下
     *      FileSystemXmlApplicationContext：可以加载磁盘任意路径下的配置文件（必须有访问权限）
     *      AnnotationConfigApplicationContext：用于读取注解创建容器
     *
     * 2.核心容器的两个接口引发出的问题
     *  ApplicationContext：
     *      它在构建核心容器时，创建对象采取得到策略是立即加载的方式.
     *  BeanFactory：
     *      它在构建核心容器时，创建对象采取得到策略是延迟加载的方式. Lazy-load
     *
     * @param args
     */
    public static void main(String[] args) {
        // 1.获取核心容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService service1 = (IAccountService) context.getBean("accountService");
        IAccountService service2 = (IAccountService) context.getBean("accountService");

        System.out.println(service1 == service2);
        context.close();

        // ------ BeanFactory ------
        /*Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        IAccountService service = factory.getBean("accountService", IAccountService.class);
        System.out.println(service);*/

    }

}
