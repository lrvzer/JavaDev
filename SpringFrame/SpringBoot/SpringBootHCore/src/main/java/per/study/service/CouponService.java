package per.study.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import per.study.entity.UserEntity;
import per.study.event.LoginSuccessEvent;

@Service
public class CouponService {

    @EventListener
    public void onEvent(LoginSuccessEvent event) {
        UserEntity entity = (UserEntity) event.getSource();
        System.out.println("CouponService --> onEvent");
        onCoupon(entity.getUsername());
    }

    public void onCoupon(String username) {
        System.out.println(username + " 随机得到了一张优惠券");
    }

}
