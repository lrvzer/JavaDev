package per.study.service.impl;

import per.study.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    @Override
    public void saveAccount() {
        System.out.println("save");
//        int i = 1/0;
    }

    @Override
    public void updateAccount(int id) {
        System.out.println("update");
    }

    @Override
    public int deleteAccount() {
        System.out.println("delete");
        return 0;
    }
}
