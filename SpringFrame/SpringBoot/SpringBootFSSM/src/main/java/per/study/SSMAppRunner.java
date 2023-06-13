package per.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "per.study.mapper") // mybatis扫描接口路径
@SpringBootApplication
public class SSMAppRunner {
    public static void main(String[] args) {
        SpringApplication.run(SSMAppRunner.class, args);
    }
}
