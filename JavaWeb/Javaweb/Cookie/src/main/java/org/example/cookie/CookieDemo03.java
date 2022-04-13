package org.example.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 中文数据传递
 */
public class CookieDemo03 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                if (cookie.getName().equals("name")) {
                    out.write(URLDecoder.decode(cookie.getValue(), "utf-8"));
                }
            }
        } else {
            out.write("这是第一次访问本站");
        }
        Cookie cookie = new Cookie("name", URLEncoder.encode("汤姆", "utf-8"));
        resp.addCookie(cookie);
    }
}
