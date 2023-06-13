package per.study.socket;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest2 {
    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 40000);
            os = socket.getOutputStream();
            fis = new FileInputStream("1.txt");
            int len;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            // 关闭数据输出
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len=is.read(buf))!=-1) {
                baos.write(buf, 0, len);
            }
            System.out.println(baos.toString());
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                os.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void server() {
        try {
            ServerSocket ss = new ServerSocket(40000);
            Socket accept = ss.accept();
            InputStream stream = accept.getInputStream();
            FileOutputStream fos = new FileOutputStream("3.txt");
            byte[] buf = new byte[1024];
            int len;
            while ((len=stream.read(buf))!=-1) {
                fos.write(buf, 0, len);
            }

            OutputStream os = accept.getOutputStream();
            os.write("你好接收完毕".getBytes());

            fos.close();
            stream.close();
            accept.close();
            ss.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
