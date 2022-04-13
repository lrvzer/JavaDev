package per.study.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import per.study.dao.IAccountDao;
import per.study.service.IAccountService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 1.用于创建对象，同xml文件中bean标签
 *      Component       value默认值为类名且首字母小写
 *      Controller      表现层
 *      Service         业务层
 *      Repository      持久层
 *
 * 2.用于注入数据
 *      Autowired
 *      Qualifier
 *      Resource
 *      Value
 *
 * 3.用于改变作用范围
 *      Scope       value=singleton/prototype
 *
 * 4.生命周期
 *
 */
@Component("accountService")
//@Scope(value = "prototype")
public class AccountServiceImpl implements IAccountService {

    /*@Autowired
    @Qualifier("accountDao")*/
    @Resource(name = "accountDao")
    private IAccountDao accountDao;

    @PostConstruct
    private void init() {
        System.out.println("init...");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("destroy...");
    }

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
