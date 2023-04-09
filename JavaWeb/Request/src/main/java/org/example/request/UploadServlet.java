package org.example.request;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;

public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Enumeration<String> headerNames = req.getHeaderNames();

        int len = req.getContentLength();
        System.out.println(req.getContentType());
        byte buffer[] = new byte[len];
        InputStream in = req.getInputStream();
        int total = 0;
        int once = 0;
        while ((total < len) && (once >= 0)) {
            once = in.read(buffer, total, len);
            total += once;
        }
        System.out.println(new String(buffer, 0, len));

//        for (int i = 0; i < len; i++) {
//            System.out.println((byte)buffer[i] + "-->" + (char)(buffer[i]));
//        }


        // 通过请求转发
        req.getRequestDispatcher("/success.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
