package per.study.factory;

import per.study.service.IAccountService;
import per.study.service.impl.AccountServiceImpl;

public class StaticFactory {

    public static IAccountService getAccountService() {
        return new AccountServiceImpl();
    }

}
