package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 下载文件
 */
public class DownloadServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取下载文件路径
        String realPath = "/Users/Lrwei/Lrwei/JavaDev/JavaWeb/Response/wallpaper.jpg";
        // 2.获得下载文件的文件名
        String fileName = realPath.substring(realPath.lastIndexOf(File.separator) + 1);
        // 3. 浏览器支持下载相关文件
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        // 4. 获取下载文件的输入流
        FileInputStream fis = new FileInputStream(realPath);
        // 5. 创建缓冲区
        int len;
        byte[] buf = new byte[1024];
        // 6. 获取OutputStream对象
        ServletOutputStream fos = resp.getOutputStream();
        // 7. 将FileOutputStream流写入到buffer缓冲区, 使用OutputStream将缓冲区中的数据输出到客户端
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        // 8. 关闭流
        fis.close();
        fos.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
