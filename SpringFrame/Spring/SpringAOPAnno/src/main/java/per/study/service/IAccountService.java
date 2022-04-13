package per.study.service;

/**
 * 账户的业务层接口
 */
public interface IAccountService {

    /*模拟保存账户*/
    void saveAccount();

    /*模拟更新账户*/
    void updateAccount(int id);

    /*删除账户*/
    int deleteAccount();
}
