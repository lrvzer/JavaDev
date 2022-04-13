package per.study.factory;

import per.study.service.IAccountService;
import per.study.service.impl.AccountServiceImpl;

public class InstantFactory {

    public IAccountService getAccountService() {
        return new AccountServiceImpl();
    }

}
