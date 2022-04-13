package per.study.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import per.study.service.IAccountService;
import per.study.service.config.SpringConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AOPTest {

    @Autowired
    private IAccountService accountService;

    @Test
    public void test() {
        accountService.saveAccount();
//        accountService.updateAccount(1);
//        accountService.deleteAccount();
    }

}
