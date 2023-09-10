package per.study.guava.io;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

public class BaseEncodingTest {
    @Test
    public void testBase64Encode() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(baseEncoding.encode("hello".getBytes()));
    }

    @Test
    public void testBase64Decode() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(new String(baseEncoding.decode("aGVsbG8=")));
    }

    @Test
    public void testMyBase64Encode() {
        System.out.println(Base64.encode("hello"));
    }


    @Test
    public void testMyBase64Decode() {
        System.out.println(Base64.decode("aGVsbG8="));
    }
}
