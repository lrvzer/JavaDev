package per.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import per.study.dao.IAccountDao;
import per.study.domain.Account;
import per.study.service.IAccountService;

@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AccountServiceImpl implements IAccountService {

    @Autowired
    @Qualifier("accountDao")
    private IAccountDao accountDao;

    @Autowired
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account findAccountByID(Integer id) {
        return accountDao.findAccountByID(id);
    }

    @Override
    public Account findAccountByName(String name) {
        return accountDao.findAccountByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void transfer(String src, String dest, float money) {
        Account source = accountDao.findAccountByName(src);
        Account target = accountDao.findAccountByName(dest);

        source.setMoney(source.getMoney() - money);
        target.setMoney(target.getMoney() + money);

        int i = 1/0;

        accountDao.updateAccount(source);
        accountDao.updateAccount(target);
    }


}
