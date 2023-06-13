package per.study.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import per.study.entity.UserEntity;
import per.study.event.LoginSuccessEvent;

@Service
public class SysService {

    @EventListener
    public void onEvent(LoginSuccessEvent event) {
        UserEntity entity = (UserEntity) event.getSource();
        System.out.println("SysService --> onEvent");
        recordLog(entity.getUsername());
    }

    public void recordLog(String username) {
        System.out.println(username + " 登陆日志");
    }

}
