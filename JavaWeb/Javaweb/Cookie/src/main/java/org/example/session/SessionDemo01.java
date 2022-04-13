package org.example.session;

import org.example.session.domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        // 得到Session
        HttpSession session = req.getSession();

        // 设置属性
        session.setAttribute("name", new Person("Tom", 11));

        // 获取Session的ID
        String sessionId = session.getId();

        // 判断Session是不是新建
        if (session.isNew()) {
            resp.getWriter().write("Session创建成功，ID：" + sessionId);
        } else {
            resp.getWriter().write("Session已经在服务器中存在了，ID：" + sessionId);
        }

        // Session创建的时候做了什么事情
//        Cookie cookie = new Cookie("JSESSIONID", sessionId);
//        resp.addCookie(cookie);

    }
}
