package per.study;

import ch.qos.logback.core.pattern.parser.Parser;
import ch.qos.logback.core.util.FileUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import per.study.bean.Pet;
import per.study.bean.User;
import per.study.config.BeanConfig;

import java.util.Arrays;

/**
 * SpringBootApplication --> scanBasePackages 指定包扫描
 */
@SpringBootApplication(scanBasePackages = {"per.study" })
public class AppRunner
{

    public static void main(String[] args) {
        // 1.返回IOC容器
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppRunner.class, args);
        // 2.查看容器内组件
        String[] names = applicationContext.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);

        // 3.获取Bean实例
        User userJerry = applicationContext.getBean("userJerry", User.class);
        User userJerry2 = applicationContext.getBean("userJerry", User.class);
        System.out.println(userJerry == userJerry2);

        /**
         * Full -- @Configuration(proxyBeanMethods = true)
         * 此时进行了方法代理：
         *      BeanConfig
         *          BeanConfig: per.study.config.BeanConfig$$EnhancerBySpringCGLIB$$314ab08f@2fb5fe30
         *      User
         *          Spring@Bean注解的实例化对象与BeanConfig.userSetByDefaultValue()获取的对象是同一个实例
         *
         * Lite -- @Configuration(proxyBeanMethods = false)
         * 此时没有进行方法代理
         *      BeanConfig
         *          BeanConfig: per.study.config.BeanConfig@6dba847b
         *      User
         *          BeanConfig.userSetByDefaultValue()获取的对象为原型模式
         */

        BeanConfig beanConfig = applicationContext.getBean(BeanConfig.class);
        System.out.println("BeanConfig: " + beanConfig);
        
        User user = beanConfig.userJerry();
        System.out.println(user == userJerry);

        Pet pet = userJerry.getPet();
        Pet tom = beanConfig.petSetByDefaultValue();
        System.out.println("pet == tom" + (pet == tom));


        System.out.println("==========");
        String[] beanNames = applicationContext.getBeanNamesForType(User.class);
        for(String bean : beanNames) {
            System.out.println(bean);
        }

    }

}
