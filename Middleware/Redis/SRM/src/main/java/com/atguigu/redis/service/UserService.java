package com.atguigu.redis.service;

import com.atguigu.redis.entities.User;
import com.atguigu.redis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService
{
    private static final String CACHE_KEY_USER = "user:";

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 新增用户
     * 
     * @param user
     *            用户
     */
    public void addUser(User user) {
        // 1.先插入mysql并成功
        int i = userMapper.insertSelective(user);

        if (i > 0) {
            // 2.需要再次查询mysql，将数据查询出来
            user = userMapper.selectByPrimaryKey(user.getId());

            // 3.将查询的数据user存进redis，完成新增功能的数据一致性
            String key = CACHE_KEY_USER + user.getId();
            redisTemplate.opsForValue().set(key, user);
        }
    }

    /**
     * 指定id删除用户
     * 
     * @param id
     *            指定id
     */
    public void deleteUser(Integer id) {
        int i = userMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            String key = CACHE_KEY_USER + id;
            redisTemplate.delete(key);
        }
    }

    /**
     * 更新用户
     * 
     * @param user
     *            指定用户
     */
    public void updateUser(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);

        if (i > 0) {
            // 2.需要再次查询mysql，将数据查询出来
            user = userMapper.selectByPrimaryKey(user.getId());

            // 3.将查询的数据user存进redis，完成新增功能的数据一致性
            String key = CACHE_KEY_USER + user.getId();
            redisTemplate.opsForValue().set(key, user);
        }
    }

    /**
     * 查找用户
     * @param id 指定id
     * @return 返回user
     */
//    public User findUserById(Integer id) {
//        User user = null;
//        String key = CACHE_KEY_USER + id;
//
//        // 1.先从redis里面查询，如果有直接返回结果，如果没有，再去查询mysql
//        user = (User) redisTemplate.opsForValue().get(key);
//
//        if (user == null) {
//            // 2.redis里面没有，继续查询mysql
//            user = userMapper.selectByPrimaryKey(id);
//            if (user == null)
//                // 3.1 redis + mysql都没有
//                return user;
//            else {
//                // 3.2 mysql有，需要将数据写会redis，保证下一次的缓存命中率
//                redisTemplate.opsForValue().set(key, user);
//            }
//        }
//        return user;
//    }
    public User findUserById(Integer id) {
        User user = null;
        String key = CACHE_KEY_USER + id;

        // 1.先从redis里面查询，如果有直接返回结果，如果没有，再去查询mysql
        user = (User) redisTemplate.opsForValue().get(key);

        if (user == null) {
            // 2.对于高QPS优化，加锁
            synchronized (UserService.class) {
                user = (User) redisTemplate.opsForValue().get(key);
                if (user == null) {
                    // 4 查询mysql中的数据
                    user = userMapper.selectByPrimaryKey(id);
                    if (user == null)
                        return null;
                    else
                        redisTemplate.opsForValue().setIfAbsent(key, user, 7L, TimeUnit.DAYS);
                }
            }
        }
        return user;
    }

}
