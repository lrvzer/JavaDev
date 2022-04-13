package per.study.ui;

import per.study.factory.BeanFactory;
import per.study.service.IAccountService;

public class Client {
    public static void main(String[] args) {
        for (int i=0; i< 5; i++) {
            IAccountService service = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(service);
        }
//        service.saveAccount();
    }
}
