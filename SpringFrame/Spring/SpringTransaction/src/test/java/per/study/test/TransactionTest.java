package per.study.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import per.study.config.SpringConfiguration;
import per.study.domain.Account;
import per.study.service.IAccountService;

@RunWith(SpringRunner.class)
//@ContextConfiguration(locations = "classpath:tx.xml")
@ContextConfiguration(classes = SpringConfiguration.class)
public class TransactionTest {

    @Autowired
    @Qualifier("accountService")
    private IAccountService accountService;

    @Test
    public void test() {
//        accountService.transfer("bbb", "aaa", 100f);
        Account aaa = accountService.findAccountByName("aaa");
        System.out.println(aaa);
    }

}
