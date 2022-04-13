package per.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import per.study.dao.IAccountDao;
import per.study.domain.Account;
import per.study.service.IAccountService;
import per.study.utils.TransactionManager;

import java.util.List;

/**
 * 业务层实现
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService
{

    @Autowired
    @Qualifier("accountDao")
    private IAccountDao accountDao;

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountByID(Integer id) {
        return accountDao.findAccountByID(id);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccountByID(Integer id) {
        accountDao.deleteAccountByID(id);
    }

    @Override
    public void transfer(String srcName, String destName, Float money) {
        Account src = accountDao.findAccountByName(srcName);
        Account dest = accountDao.findAccountByName(destName);
        src.setMoney(src.getMoney() - money);
        dest.setMoney(dest.getMoney() + money);
        int i = 1/0;
        updateAccount(src);
        updateAccount(dest);
    }
}
