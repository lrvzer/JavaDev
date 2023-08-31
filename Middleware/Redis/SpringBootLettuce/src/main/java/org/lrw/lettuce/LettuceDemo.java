package org.lrw.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.List;

public class LettuceDemo {
    public static void main(String[] args) {
        // 1. 使用构建器链式编程，来builder RedisURI
        RedisURI uri = RedisURI.builder()
                .withHost("127.0.0.1")
                .withPort(6379)
                .withAuthentication("default", "PI3.1415926IP")
                .build();

        // 2. 创建链接客户端
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection conn = redisClient.connect();

        // 3. 创建操作的command，通过conn创建
        RedisCommands commands = conn.sync();

        // =====biz=====
        // keys
        List keys = commands.keys("*");
        System.out.println(keys);

        // string
        commands.set("k5", "hello-lettuce");
        System.out.println(commands.get("k5"));

        // =====biz=====

        // 4. 释放资源
        conn.close();
        redisClient.shutdown();
    }
}
