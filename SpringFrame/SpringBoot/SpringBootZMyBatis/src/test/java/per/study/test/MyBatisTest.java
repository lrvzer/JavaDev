package per.study.test;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import per.study.ApplicationStart;
import per.study.mapper.UserMapper;

@SpringBootTest(classes = ApplicationStart.class)
public class MyBatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        userMapper.queryList().stream().forEach(System.out::println);
    }

}
