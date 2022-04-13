package per.study.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import per.study.config.SpringConfiguration;
import per.study.domain.Account;
import per.study.service.IAccountService;

import java.util.List;

/**
 * 使用Junit单元测试
 * Spring整合Junit的配置
 *  1.在pom.xml文件中导入spring-test的坐标
 *  2.使用Junit提供的注解@RunWith，把原有的main方法替换成Spring提供的
 *  3.告知Spring的运行器，SpringIOC容器的创建是基于XML文件还是注解
 *      @ContextConfiguration
 *              classes         基于注解
 *              locations       基于XML文件
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

    @Autowired
    private IAccountService accountService;

    @Test
    public void findAllAccountTest() {
        List<Account> accounts = accountService.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void findAccountByIDTest() {
        Account account = accountService.findAccountByID(2);
        System.out.println(account);
    }

    @Test
    public void saveAccountTest() {
        Account account = new Account();
        account.setName("Tom");
        account.setMoney(10000F);

        accountService.saveAccount(account);
    }

    @Test
    public void updateAccountTest() {
        Account account = accountService.findAccountByID(3);
        account.setMoney(999F);
        accountService.updateAccount(account);
    }

    @Test
    public void deleteAccountByIDTest() {
        accountService.deleteAccountByID(5);
    }

    @Test
    public void transferTest() {
        accountService.transfer("aaa", "bbb", 100F);
    }

}
