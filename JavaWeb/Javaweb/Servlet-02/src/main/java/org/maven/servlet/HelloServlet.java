package org.maven.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        this.getInitParameter(String); // 初始化参数
//        this.getServletConfig(); // Servlet配置
//        this.getServletContext(); // Servlet上下文
        ServletContext context = this.getServletContext();
        context.setAttribute("username", "Tom");

    }
}
