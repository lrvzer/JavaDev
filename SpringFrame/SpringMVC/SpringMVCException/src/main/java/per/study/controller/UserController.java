package per.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import per.study.exception.SysException;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/testException")
    public String testException() throws SysException {
        System.out.println("testException");

        try {
            int i = 1/0; /*模拟异常*/
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("异常");
        }
        return "success";
    }

}
