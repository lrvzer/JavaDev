package per.study;

import java.io.IOException;
import java.net.InetAddress;

public class Main
{
    public static void main(String[] args) throws IOException {
        // args[0]: 本机名 args[1]：缓冲时间
        if (args.length < 2)
            return;
        java.security.Security.setProperty("networkaddress.cache.ttl", args[1]);
        long time = System.currentTimeMillis();
        InetAddress addresses1[] = InetAddress.getAllByName(args[0]);
        System.out.println("addresses1:   " + (System.currentTimeMillis() - time) + "毫秒");
        for (InetAddress address : addresses1)
            System.out.println(address);
        System.out.print("按任意键继续");
        System.in.read();
        time = System.currentTimeMillis();
        InetAddress addresses2[] = InetAddress.getAllByName(args[0]);
        System.out.println("addresses2:   " + (System.currentTimeMillis() - time) + "毫秒");
        for (InetAddress address : addresses2)
            System.out.println(address);
    }
}
