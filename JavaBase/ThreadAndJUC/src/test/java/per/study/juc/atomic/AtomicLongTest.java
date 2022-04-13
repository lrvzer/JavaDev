package per.study.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

public class AtomicLongTest {

    @Test
    public void testCreate() {
        AtomicLong i = new AtomicLong(100L);
        /**
         * 32 bit
         * long 64
         * high 32
         * low  32
         */
        assertEquals(100L, i.get());
    }

}
