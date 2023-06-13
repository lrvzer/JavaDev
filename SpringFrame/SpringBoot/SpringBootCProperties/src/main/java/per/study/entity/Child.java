package per.study.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/4
 **/
@Data
public class Child {
    private String name;
    private Integer age;
    private Date birthDay;
    private List<String> text;
}
