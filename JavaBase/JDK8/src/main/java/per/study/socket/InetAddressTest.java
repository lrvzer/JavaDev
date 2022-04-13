package per.study.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lrwei
 */
public class InetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress byName = InetAddress.getByName("www.baidu.com");
            System.out.println(byName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
