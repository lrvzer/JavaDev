package per.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebAppRunner {
    public static void main(String[] args) {
        SpringApplication.run(WebAppRunner.class, args);
    }
}

/**
 * SpringMVC的所有配置 spring.mvc
 * Web场景通用配置 spring.web12221
 * 文件上传配置 spring.servlet.multipart
 * 服务器的配置 server 比如：编码
 */