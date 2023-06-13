package per.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/4
 **/
@RestController
public class LoggerController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/hello")
    public String hello() {
        logger.info("hello, world");
        return "hello";
    }
}
