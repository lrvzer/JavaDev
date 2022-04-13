package per.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import per.study.domain.User;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"})
public class AnnoController {

    /**
     * RequestParam注解
     * @param username
     * @return
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam("name") String username) {
        System.out.println("username: " + username);
        return "success";
    }

    /**
     * RequestBody注解
     * @param body
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println("body: " + body);
        return "success";
    }

    /**
     * PathVariable注解
     * @param id
     * @return
     */
    @RequestMapping("/testPathVariable/{sid}")
    public String testPathVariable(@PathVariable("sid") String id) {
        System.out.println("id: " + id);
        return "success";
    }

    /**
     * RequestHeader注解
     * @param header
     * @return
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header) {
        System.out.println("header: " + header);
        return "success";
    }

    /**
     * CookieValue注解
     * @param cookieValue
     * @return
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue) {
        System.out.println("cookieValue: " + cookieValue);
        return "success";
    }

    /**
     * ModelAttribute注解
     * @param user
     * @return
     */
    @RequestMapping("/testModelAttribute")
//    public String testModelAttribute(User user) { // getUser方法有返回值
    public String testModelAttribute(@ModelAttribute("abc") User user) { // getUser方法无返回值
        System.out.println("user: " + user);
        return "success";
    }
    /*
    @ModelAttribute
    public User getUser(String uname) {
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        return user;
    }
    */

    @ModelAttribute
    public void getUser(String uname, Map<String, User> map) {
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        map.put("abc", user);
    }


    @RequestMapping("/setSessionAttributes")
    public String setSessionAttributes(Model model) {
        model.addAttribute("msg", "hello");
        return "success";
    }

    @RequestMapping("/getSessionAttributes")
    public String getSessionAttributes(ModelMap model) {
        String msg = (String) model.get("msg");
        System.out.println(msg);
        return "success";
    }

    @RequestMapping("/delSessionAttributes")
    public String delSessionAttributes(SessionStatus status) {
        status.setComplete();
        return "success";
    }

}
