package per.study.jdbc.template;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import per.study.dao.IAccountDao;
import per.study.domain.Account;

public class JdbcTemplateDemo4 {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDao accountDao = context.getBean("accountDao", IAccountDao.class);

        Account account = accountDao.findAccountByName("aaa");
        System.out.println(account);

        account.setMoney(10000f);
        accountDao.updateAccount(account);

        Account accountByID = accountDao.findAccountByID(1);
        System.out.println(accountByID);

    }

}
