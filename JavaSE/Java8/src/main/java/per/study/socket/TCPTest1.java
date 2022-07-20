package per.study.socket;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 */
public class TCPTest1 {

    @Test
    public void client() {
        InetAddress inetAddress;
        Socket socket = null;
        OutputStream os = null;
        try {
            // 1、创建Socket对象，指明服务端的ip和port
            inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress, 8899);
            // 2、获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            // 3、写出数据的操作
            os.write("你好".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 4、释放资源
                os.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void server() {
        ServerSocket ss = null;
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        Socket accept = null;
        try {
            // 1、创建服务器段的ServerSocket，指明port
            ss = new ServerSocket(8899);
            // 2、调用accept()表示接受来自客户端的socket
            accept = ss.accept();
            // 3、获取输入流
            is = accept.getInputStream();
            int len;
            byte[] buf = new byte[1024];
            bos = new ByteArrayOutputStream();
            // 4、获取来自客户端的数据
            while ((len=is.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            System.out.println(bos.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                 bos.close();
                 is.close();
                 accept.close();
                 ss.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
