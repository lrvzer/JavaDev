package per.study.ui;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import per.study.service.IAccountService;

public class Client
{
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        IAccountService service1 = (IAccountService) context.getBean("accountService");
        IAccountService service2 = (IAccountService) context.getBean("accountService");
        System.out.println(service1 == service2);
        service1.saveAccount();
        context.close();
    }
}
