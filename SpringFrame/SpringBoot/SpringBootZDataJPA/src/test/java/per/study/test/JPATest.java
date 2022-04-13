package per.study.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import per.study.ApplicationStart;
import per.study.repository.UserRepository;

@SpringBootTest(classes = ApplicationStart.class)
class JPATest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void test() {
        userRepository.findAll().forEach(System.out::println);
    }
}
