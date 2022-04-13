package per.study.dao.impl;

import per.study.dao.IAccountDao;

public class AccountDaoImpl implements IAccountDao
{
    @Override
    public void saveAccount() {
        System.out.println("save account");
    }
}
