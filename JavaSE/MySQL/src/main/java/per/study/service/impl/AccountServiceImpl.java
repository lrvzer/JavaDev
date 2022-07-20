package per.study.service.impl;

import per.study.dao.IAccountDao;
import per.study.factory.BeanFactory;
import per.study.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
