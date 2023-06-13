package per.study.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.study.entity.Person;

@Slf4j
@RestController
public class HelloController {

    /**
     * 默认使用 PathPatternParser 进行路径匹配
     * 不能匹配 ** 在中间的情况，剩下的和 antPathMatcher 语法兼容
     *
     * @param request
     * @param path
     * @return
     */
//    @GetMapping("/a*/b?/{p1:[a-f]+}/**")
    @GetMapping("/a*/b?/**/{p1:[a-f]+}/**")
    public String hello(HttpServletRequest request, @PathVariable("p1") String path) {
        log.info("路径变量p1: {}", path);
        return request.getRequestURI();
    }

    // 内容协商
    @RequestMapping("/person")
    public Person person() {
        Person person = new Person(1L, "zhangSan", 10, "aa@qq.com");
        return person;
    }

    @RequestMapping("/test/error")
    public void error() {
        int i = 10 / 0;
    }

}
