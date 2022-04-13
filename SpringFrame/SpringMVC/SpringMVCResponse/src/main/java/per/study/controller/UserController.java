package per.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import per.study.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 返回String类型
     * @param model
     * @return
     */
    @RequestMapping("/testString")
    public String testString(Model model) {
        System.out.println("testString");

        /* 将user发送给页面 */
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        user.setAge(20);
        model.addAttribute("user", user);

        return "success";
    }

    /**
     * 返回值void
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("testVoid");

        /*请求转发*/
        // request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);

        /*重定向*/
        // response.sendRedirect(request.getContextPath() + "/index.jsp");

        /*处理中文乱码*/
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        /*直接响应*/
        response.getWriter().print("你好");

        return;
    }

    /**
     * ModelAndView
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView mv = new ModelAndView();

        User user = new User();
        user.setUsername("李四");
        user.setPassword("123qwe");
        user.setAge(20);

        /*将user对象存入ModelAndView，也会存入request对象中*/
        mv.addObject("user", user);

        /*跳转页面设置*/
        mv.setViewName("success");
        return mv;
    }

    /**
     * 请求转发
     * @return
     */
    @RequestMapping("/testForward")
    public String testForward() {
        System.out.println("testForward");
        return "forward:/WEB-INF/views/success.jsp";
    }

    /**
     * 重定向
     * @return
     */
    @RequestMapping("/testRedirect")
    public String testRedirect() {
        System.out.println("testRedirect");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user) {
        System.out.println(user);
        user.setUsername("李四");
        user.setPassword("eee333");
        user.setAge(30);
        return user;
    }

}
