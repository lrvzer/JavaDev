package per.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import per.study.bean.Pet;
import per.study.bean.User;

@Import({
        User.class
        // 在容器中自动创建引入类型的组件
        // 组件的默认名就是全类名，比如：per.study.bean.User
})
@Configuration(proxyBeanMethods = true)
public class BeanConfig {

    @Bean
    public User userJerry() {
        User jerry = new User("Jerry", 10);
        jerry.setPet(petSetByDefaultValue());
        return jerry;
    }

    @Bean("tom")
    public Pet petSetByDefaultValue() {
        return new Pet("Tomcat");
    }

}
