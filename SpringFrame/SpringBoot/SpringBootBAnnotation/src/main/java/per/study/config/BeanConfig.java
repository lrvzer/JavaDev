package per.study.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import per.study.bean.Cat;
import per.study.bean.Pig;
import per.study.bean.Sheep;
import per.study.bean.User;

@Import({
        // 假设此时User类为第三方Jar包中的类，即可以用@Bean注解实现组件注册，也可通过@Import注解实现注册
        User.class
        // 在容器中自动创建引入类型的组件
        // 组件的默认名就是全类名，比如：per.study.bean.User；与@Bean的默认名不同
})
// 开启Sheep组件的属性绑定，并将组件加入容器
// 导入第三方写好的组件进行属性绑定
@EnableConfigurationProperties(Sheep.class)
@Configuration(proxyBeanMethods = true)
public class BeanConfig {

    /**
     * Annotation @Bean: 组件在容器中的名字默认为方法名；可以在直接修改注解的值
     * Annotation @Scope: 默认单例
     */

    @Bean
    public User userJerry() {
        User jerry = new User("Jerry", 10);
        jerry.setPet(petSetByDefaultValue());
        return jerry;
    }

    @Bean("tom")
    public Cat petSetByDefaultValue() {
        return new Cat("Tomcat");
    }

    @ConfigurationProperties(prefix = "pig") // 属性绑定
    @Bean
    public Pig pig() {
        return new Pig();
    }

}
