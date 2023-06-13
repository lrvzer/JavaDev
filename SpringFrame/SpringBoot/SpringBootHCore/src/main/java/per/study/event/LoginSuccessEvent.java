package per.study.event;

import org.springframework.context.ApplicationEvent;
import per.study.entity.UserEntity;

public class LoginSuccessEvent extends ApplicationEvent {
    public LoginSuccessEvent(UserEntity source) {
        super(source);
    }
}
