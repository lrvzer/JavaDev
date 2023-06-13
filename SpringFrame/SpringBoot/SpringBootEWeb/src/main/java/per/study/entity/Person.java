package per.study.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JacksonXmlRootElement
@Data
public class Person {
    private long id;
    private String username;
    private int age;
    private String email;

    public Person(long id, String username, int age, String email) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.email = email;
    }

    public Person() {
    }
}
