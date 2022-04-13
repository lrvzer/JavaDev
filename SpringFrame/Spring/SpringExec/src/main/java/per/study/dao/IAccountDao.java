package per.study.dao;

import per.study.domain.Account;

import java.util.List;

public interface IAccountDao {

    /* 查询所有 */
    List<Account> findAllAccount();

    /* 按ID查询 */
    Account findAccountByID(Integer id);

    /* 保存 */
    void saveAccount(Account account);

    /* 更新 */
    void updateAccount(Account account);

    /* 按ID删除 */
    void deleteAccountByID(Integer id);

    Account findAccountByName(String name);
}
