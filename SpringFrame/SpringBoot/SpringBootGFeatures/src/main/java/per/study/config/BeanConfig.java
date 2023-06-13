package per.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import per.study.bean.Cat;
import per.study.bean.Dog;
import per.study.bean.Pig;
import per.study.bean.Sheep;

@Configuration
public class BeanConfig {

    @Profile({"dev"})
    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Profile({"test"})
    @Bean
    public Dog dog() {
        return new Dog();
    }

    @Profile("prod")
    @Bean
    public Pig pig() {
        return new Pig();
    }

    @Bean
    public Sheep sheep() {
        return new Sheep();
    }
}
