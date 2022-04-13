package per.study.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

public class UDPTest {
    @Test
    public void sender() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        String s = "UDP sender message";
        byte[] buf = s.getBytes();
        DatagramPacket dgp = new DatagramPacket(buf, 0, buf.length, InetAddress.getLocalHost(), 40001);
        socket.send(dgp);
        socket.close();
    }

    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(40001);
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
        socket.receive(datagramPacket);
        System.out.println(new String(datagramPacket.getData()));
        socket.close();
    }
}
