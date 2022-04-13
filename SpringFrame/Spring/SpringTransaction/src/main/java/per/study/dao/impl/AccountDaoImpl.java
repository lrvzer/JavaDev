package per.study.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import per.study.dao.IAccountDao;
import per.study.domain.Account;

import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao
{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findAccountByID(Integer id) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?",
                new BeanPropertyRowMapper<>(Account.class), id);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Account findAccountByName(String name) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?",
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
        jdbcTemplate.update("update account set name = ?, money = ? where id = ?", account.getName(),
                account.getMoney(), account.getId());
    }
}
