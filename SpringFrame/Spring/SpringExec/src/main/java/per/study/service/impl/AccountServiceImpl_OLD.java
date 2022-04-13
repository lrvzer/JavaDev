package per.study.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import per.study.dao.IAccountDao;
import per.study.domain.Account;
import per.study.service.IAccountService;
import per.study.utils.TransactionManager;

/**
 * 业务层实现
 */
@Service("accountServiceOLD")
public class AccountServiceImpl_OLD implements IAccountService {

    @Autowired
    @Qualifier("accountDao")
    private IAccountDao accountDao;

    @Autowired
    private TransactionManager txManager;

    @Override
    public List<Account> findAllAccount() {
        try {
            // 1.开启事务
            txManager.begin();

            // 2.执行操作
            List<Account> accounts = accountDao.findAllAccount();

            // 3.提交事务
            txManager.commit();

            // 4.返回结果
            return accounts;
        } catch (Exception e) {
            // 5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 6.释放连接
            txManager.release();
        }
    }

    @Override
    public Account findAccountByID(Integer id) {
        try {
            // 1.开启事务
            txManager.begin();

            // 2.执行操作
            Account account = accountDao.findAccountByID(id);

            // 3.提交事务
            txManager.commit();

            // 4.返回结果
            return account;
        } catch (Exception e) {
            // 5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 6.释放连接
            txManager.release();
        }

    }

    @Override
    public void saveAccount(Account account) {
        try {
            // 1.开启事务
            txManager.begin();

            // 2.执行操作
            accountDao.saveAccount(account);

            // 3.提交事务
            txManager.commit();

            // 4.返回结果
        } catch (Exception e) {
            // 5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 6.释放连接
            txManager.release();
        }

    }

    @Override
    public void updateAccount(Account account) {
        try {
            // 1.开启事务
            txManager.begin();

            // 2.执行操作
            accountDao.updateAccount(account);

            // 3.提交事务
            txManager.commit();

            // 4.返回结果
        } catch (Exception e) {
            // 5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 6.释放连接
            txManager.release();
        }

    }

    @Override
    public void deleteAccountByID(Integer id) {
        try {
            // 1.开启事务
            txManager.begin();

            // 2.执行操作
            accountDao.deleteAccountByID(id);

            // 3.提交事务
            txManager.commit();

            // 4.返回结果
        } catch (Exception e) {
            // 5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 6.释放连接
            txManager.release();
        }

    }

    @Override
    public void transfer(String srcName, String destName, Float money) {
        try {
            // 1.开启事务
            txManager.begin();

            // 2.执行操作
            Account src = accountDao.findAccountByName(srcName);
            Account dest = accountDao.findAccountByName(destName);
            src.setMoney(src.getMoney() - money);
            dest.setMoney(dest.getMoney() + money);
            updateAccount(src);
            updateAccount(dest);
            int i = 1/0;

            // 3.提交事务
            txManager.commit();

            // 4.返回结果
        } catch (Exception e) {
            // 5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 6.释放连接
            txManager.release();
        }

    }
}
