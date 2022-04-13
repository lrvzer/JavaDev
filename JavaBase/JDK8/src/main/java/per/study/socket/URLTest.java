package per.study.socket;

import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {

    @Test
    public void useMethod() throws MalformedURLException {
        URL url = new URL("https://lawei.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F01c59448-e4e2-4dfa-8b8c-6f90b50cbc72%2FMySQL.png?table=block&id=2b816958-e172-4bf2-9578-186e080592d7&spaceId=1858f7a9-aa21-4bd3-b378-9744f72312fe&width=2880&userId=&cache=v2");
        // 获取URL协议名
        System.out.println(url.getProtocol());
        // 获取URL主机名
        System.out.println(url.getHost());
        // 获取URL端口号
        System.out.println(url.getPort());
        // 获取URL文件路径
        System.out.println(url.getPath());
        // 获取URL文件名
        System.out.println(url.getFile());
        // 获取URL查询名
        System.out.println(url.getQuery());
    }

    @Test
    public void getFileToLocal() {
        HttpsURLConnection urlConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL("https://lawei.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F01c59448-e4e2-4dfa-8b8c-6f90b50cbc72%2FMySQL.png?table=block&id=2b816958-e172-4bf2-9578-186e080592d7&spaceId=1858f7a9-aa21-4bd3-b378-9744f72312fe&width=2880&userId=&cache=v2");
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            urlConnection.connect();
            is = urlConnection.getInputStream();
            fos = new FileOutputStream("mysql.png");
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf))!=-1) {
                fos.write(buf, 0, len);
            }
            System.out.println("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }


    }

}
