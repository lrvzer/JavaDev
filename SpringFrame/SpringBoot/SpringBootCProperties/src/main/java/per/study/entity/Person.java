package per.study.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/4
 **/
@ConfigurationProperties(prefix = "person") // 和配置文件person前缀的所有配置进行绑定
@Component
@Data // 自动生成JavaBean属性的getter/setter
//@NoArgsConstructor // 自动生成无参构造器
//@AllArgsConstructor // 自动生成全参构造器
public class Person {
    private String name;
    private Integer age;
    private Date birthDay;
    private Boolean like;
    private Child child;
    private List<Dog> dogs;
    private Map<String, Cat> cats;
}
