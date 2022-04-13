package per.study.service.impl;

import per.study.service.IAccountService;

public class AccountServiceImpl implements IAccountService
{

    public AccountServiceImpl() {
        System.out.println("对象创建");
    }

    public void init() {
        System.out.println("init object...");
    }

    public void destroy() {
        System.out.println("destroy object...");
    }

    @Override
    public void saveAccount() {
        System.out.println("save account");
    }
}
