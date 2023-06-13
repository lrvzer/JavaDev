package per.study.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import per.study.bean.Cat;
import per.study.bean.Dog;
import per.study.bean.User;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/3
 **/
// 放在类级别，如果注解判断生效，则整个配置类生效
@ConditionalOnMissingClass(value = "com.alibaba.druid.FastsqlException")
@Configuration
public class ConditionConfig {

    @ConditionalOnClass(name = "com.alibaba.druid.FastsqlException")
    @Bean
    public Cat pet() {
        return new Cat();
    }

    // 放在方法级别，如果注解判断生效，则对这个方法配置生效
    @ConditionalOnMissingClass(value = "com.alibaba.druid.FastsqlException")
    @Bean
    public Dog dog() {
        return new Dog();
    }

    @ConditionalOnBean(value = Dog.class)
    @Bean
    public User liSi() {
        return new User("李四", 18);
    }

    @ConditionalOnMissingBean(value = Dog.class)
    @Bean
    public User zhangSan() {
        return new User("张三", 18);
    }

}
