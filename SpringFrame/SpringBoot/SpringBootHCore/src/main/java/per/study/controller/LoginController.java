package per.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.study.entity.UserEntity;
import per.study.event.EventPublisher;
import per.study.event.LoginSuccessEvent;

@RestController
public class LoginController {

    @Autowired
    EventPublisher eventPublisher;

    @GetMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("passwd") String passwd) {
        // 1.创建事件信息
        LoginSuccessEvent event = new LoginSuccessEvent(new UserEntity(username, passwd));
        // 2.发送事件
        eventPublisher.sendEvent(event);
//         业务处理登录
//        1、账户服务自动签到加积分
//        2、优惠服务随机发放优惠券
//        3、系统服务登记用户登录的信息
        return username + "登录成功";
    }

}
