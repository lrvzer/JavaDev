package per.study.service;

import per.study.domain.Account;

import java.util.List;

public interface IAccountService {

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

    /* 转账 */
    void transfer(String srcName, String destName, Float money);
}
