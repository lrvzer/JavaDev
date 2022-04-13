package per.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import per.study.domain.Account;
import per.study.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/param")
public class ParamController {

    @RequestMapping("/testParam")
    public String testParam(String username) {
        System.out.println("username: " + username);
        return "success";
    }

    @RequestMapping("/saveAccount")
    public String saveAccount(Account account) {
        System.out.println(account);
        return "success";
    }

    @RequestMapping("/saveUser")
    public String saveUser(User user) {
        System.out.println(user);
        return "success";
    }

    @RequestMapping("/testServlet")
    public String testServlet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getContextPath());
        return "success";
    }

}
