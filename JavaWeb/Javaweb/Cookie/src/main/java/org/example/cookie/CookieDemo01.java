package org.example.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 保存用户上一次访问的时间
 */
public class CookieDemo01 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        // Cookie 服务器端从客户端获取
        Cookie[] cookies = req.getCookies(); // 获取Cookie

        // 判断Cookie是否存在
        if (cookies != null) {
            out.write("上次访问的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];

                // 获取cookie的名字
                if (cookie.getName().equals("lastLoginTime")) {
                    // 获取cookie中的值
                    long lastLoginTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastLoginTime);
                    out.write(date.toLocaleString());
                }
            }
        } else {
            out.write("这是第一次访问本站");
        }

        // 服务器给客户端响应一个cookie
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");

        // 设置cookie的有效期为一天
        cookie.setMaxAge(24 * 60 * 60);

        // 响应给客户端一个cookie
        resp.addCookie(cookie);
    }
}
