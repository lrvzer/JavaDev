package per.study;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import per.study.service.HelloService;

import java.util.stream.Stream;

@SpringBootTest
public class SpringRelationTest {

    @Autowired
    HelloService helloService;

    @DisplayName("aaaa")
    @Test
    void test1() {
        Assertions.assertEquals(helloService.sum(1, 2), 3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    @DisplayName("参数化测试1")
    public void parameterizedTest1(String string) {
        System.out.println(string);
        Assertions.assertTrue(StringUtils.isNotBlank(string));
    }

    @ParameterizedTest
    @MethodSource("method")    //指定方法名
    @DisplayName("方法来源参数")
    public void testWithExplicitLocalMethodSource(String name) {
        System.out.println(name);
        Assertions.assertNotNull(name);
    }

    static Stream<String> method() {
        return Stream.of("apple", "banana");
    }

    @DisplayName("bbbb")
    @Test
    void test2() {
        System.out.println(helloService.sum(1, 2));
    }

    // once
    @BeforeAll
    static void initAll() {
        System.out.println("hello");
    }

    @BeforeEach
    void init() {
        System.out.println("every test.");
    }
}
