package org.lrw.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisDemo {
    public static void main(String[] args) {
        // 1. Connect to redis server，by IP and Port
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2. Passwd
        jedis.auth("PI3.1415926IP");
        // 3. Access to redis
        System.out.println(jedis.ping());

        // keys *
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        // string
        jedis.set("k1", "hello-jedis");
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.ttl("k1"));
        jedis.expire("k1", 20L);

        // list
        jedis.lpush("mylist", "11", "22", "33");
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        for (String element : mylist) {
            System.out.println(element);
        }
    }
}
