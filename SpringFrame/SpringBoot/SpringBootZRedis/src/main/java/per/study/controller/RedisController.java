package per.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/4
 **/
@RestController
public class RedisController {
    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/incr", method = RequestMethod.GET)
    public String incr() {
        Long haha = redisTemplate.opsForValue().increment("haha");
        return "增加后的值：" + haha;
    }
}
