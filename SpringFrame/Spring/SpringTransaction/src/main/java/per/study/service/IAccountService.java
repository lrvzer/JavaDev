package per.study.service;

import per.study.domain.Account;

public interface IAccountService {
    Account findAccountByID(Integer id);
    Account findAccountByName(String name);
    void transfer(String src, String dest, float money);
}
