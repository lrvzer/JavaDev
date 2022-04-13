package per.study.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import per.study.domain.Person1;
import per.study.domain.Person2;
import per.study.domain.Person3;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//        Person1 person = context.getBean("person1", Person1.class);
//        Person2 person = context.getBean("person2", Person2.class);
        Person3 person = context.getBean("person3", Person3.class);
        System.out.println(person);
    }
}
