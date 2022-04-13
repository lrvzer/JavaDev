package per.study.servlet;

import per.study.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println(username);
        if (username.equals("admin")) { /*登录成功*/
            req.getSession().setAttribute(Constant.USERSESSION, req.getSession().getId());
            resp.sendRedirect("/sys/success.jsp");
        } else { /*登录失败*/
            resp.sendRedirect("/error.jsp");
        }
    }
}
