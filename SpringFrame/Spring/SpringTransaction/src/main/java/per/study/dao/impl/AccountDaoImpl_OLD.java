package per.study.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import per.study.dao.IAccountDao;
import per.study.domain.Account;

//@Repository("accountDaoOLD")
public class AccountDaoImpl_OLD extends JdbcDaoSupport implements IAccountDao
{
    @Override
    public Account findAccountByID(Integer id) {
        List<Account> accounts = getJdbcTemplate().query("select * from account where id = ?",
                new BeanPropertyRowMapper<>(Account.class), id);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Account findAccountByName(String name) {
        List<Account> accounts = getJdbcTemplate().query("select * from account where name = ?",
                new BeanPropertyRowMapper<>(Account.class), name);
        if (accounts.isEmpty()) {
            return null;
        }

        if (accounts.size() > 1) {
            throw new RuntimeException("result not only one");
        }
        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        getJdbcTemplate().update("update account set name = ?, money = ? where id = ?", account.getName(),
                account.getMoney(), account.getId());
    }
}
