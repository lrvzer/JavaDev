package study.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class OrderService {
    public static final String ORDER_KEY = "ord:";

    @Resource
    private RedisTemplate redisTemplate;
//    @Resource private StringRedisTemplate StringRedisTemplate;

    public void addOrder() {
        int keyId = ThreadLocalRandom.current().nextInt(1000) + 1;
        String serialNo = UUID.randomUUID().toString();

        String key = ORDER_KEY + keyId;
        String value = "京东订单" + serialNo;

        redisTemplate.opsForValue().set(key, value);
//        StringRedisTemplate.opsForValue().set(key, value);

        log.info("key: {}, value: {}", key, value);
    }


    public String getOrderById(Integer keyId) {
        return (String) redisTemplate.opsForValue().get(ORDER_KEY + keyId);
//        return StringRedisTemplate.opsForValue().get(ORDER_KEY + keyId);
    }

}
