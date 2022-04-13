package per.study.dao;

import per.study.domain.Account;

public interface IAccountDao {

    Account findAccountByID(Integer id);
    Account findAccountByName(String name);
    void updateAccount(Account account);

}
