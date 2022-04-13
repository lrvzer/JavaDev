package per.study.service.impl;

import org.springframework.stereotype.Service;
import per.study.service.IAccountService;

@Service("accountService")
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
