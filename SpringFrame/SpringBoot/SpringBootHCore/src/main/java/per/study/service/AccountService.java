package per.study.service;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import per.study.entity.UserEntity;
import per.study.event.LoginSuccessEvent;

@Service
public class AccountService implements ApplicationListener<LoginSuccessEvent> {

    public void addAccountService(String username) {

    }

    @Override
    public void onApplicationEvent(LoginSuccessEvent event) {
        UserEntity entity = (UserEntity) event.getSource();
        System.out.println("AccountService --> onApplicationEvent");
        System.out.println(entity.getUsername() + " 积分加1");
    }
}
