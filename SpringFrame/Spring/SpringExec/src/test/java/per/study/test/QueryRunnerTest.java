package per.study.test;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import per.study.config.SpringConfiguration;
import per.study.domain.Account;
import per.study.factory.BeanFactory;
import per.study.service.IAccountService;
import per.study.utils.ConnectionUtils;
import per.study.utils.TransactionManager;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class QueryRunnerTest {

    @Qualifier("accountService")
    @Autowired
    private IAccountService service;

    @Autowired
    @Qualifier("proxyAccountService")
    private IAccountService accountService;

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        QueryRunner queryRunner1 = (QueryRunner) context.getBean("queryRunner");
        QueryRunner queryRunner2 = (QueryRunner) context.getBean("queryRunner");
        System.out.println(queryRunner1 == queryRunner2);
    }

    @Test
    public void transferTest() {
        service.transfer("aaa", "bbb", 100F);
    }

    @Test
    public void t() {
        List<Account> accounts = accountService.findAllAccount();
        System.out.println(accounts);
    }

}
