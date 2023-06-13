package per.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import per.study.entity.Person;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/4
 **/
@SpringBootApplication
public class PropertiesAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PropertiesAppRunner.class, args);
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }
}
