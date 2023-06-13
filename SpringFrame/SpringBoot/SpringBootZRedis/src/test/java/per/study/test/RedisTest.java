//package per.study.test;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import per.study.ApplicationStart;
//import per.study.domain.User;
//import per.study.repository.UserRepository;
//
//import java.util.List;
//
//@SpringBootTest(classes = ApplicationStart.class)
//class RedisTest {
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void test() throws JsonProcessingException {
//        // 1.从redis中获得数据集，数据形式：JSON
//        String userListJson = redisTemplate.boundValueOps("user.findAll").get();
//        // 2.判断redis是否存在数据
//        if (null == userListJson) {
//            // 3.不存在数据，从数据库查询
//            List<User> users = userRepository.findAll();
//            // 4.将查询出的数据存储到redis缓存中
//            ObjectMapper objectMapper = new ObjectMapper();
//            userListJson = objectMapper.writeValueAsString(users);
//            redisTemplate.boundValueOps("user.findAll").set(userListJson);
//            System.out.println("====从数据库中获得user的数据=====");
//        } else {
//            System.out.println("====从redis缓存中获得user的数据=====");
//        }
//        // 4.将数据在控制台打印
//        System.out.println(userListJson);
//    }
//
//}
