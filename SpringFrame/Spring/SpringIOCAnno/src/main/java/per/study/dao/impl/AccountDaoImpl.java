package per.study.dao.impl;

import org.springframework.stereotype.Repository;
import per.study.dao.IAccountDao;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao
{
    @Override
    public void saveAccount() {
        System.out.println("save account");
    }
}
