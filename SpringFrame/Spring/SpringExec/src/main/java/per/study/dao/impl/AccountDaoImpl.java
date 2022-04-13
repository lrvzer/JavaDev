package per.study.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import per.study.dao.IAccountDao;
import per.study.domain.Account;
import per.study.utils.ConnectionUtils;

import java.sql.SQLException;
import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao
{

    @Autowired
    private QueryRunner queryRunner;

    @Autowired
    private ConnectionUtils connectionUtils;

    @Override
    public List<Account> findAllAccount() {
        try {
            return queryRunner.query(connectionUtils.getThreadConnection(), "select * from account", new BeanListHandler<>(Account.class));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByID(Integer id) {
        try {
            return queryRunner.query(connectionUtils.getThreadConnection(), "select * from account where id = ?", new BeanHandler<>(Account.class), id);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(), "insert into account(name, money) values(?, ?)", account.getName(), account.getMoney());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(), "update account set name = ?, money = ? where id = ?", account.getName(),
                    account.getMoney(), account.getId());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccountByID(Integer id) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(), "delete from account where id = ?", id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account findAccountByName(String name) {
        try {
            List<Account> accounts = queryRunner.query(connectionUtils.getThreadConnection(), "select * from account where name = ?", new BeanListHandler<>(Account.class), name);
            if (accounts == null || accounts.size() == 0)
                return null;
            if (accounts.size() > 1)
                throw new RuntimeException("结果集不唯一，数据有问题");
            return accounts.get(0);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
