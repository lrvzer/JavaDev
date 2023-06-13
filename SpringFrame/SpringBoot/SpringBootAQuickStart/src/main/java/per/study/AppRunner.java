package per.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 声明该类是一个SpringBoot引导类
 */
@SpringBootApplication
public class AppRunner {

    /* main方法是程序的入口 */
    public static void main(String[] args) {
        /* run方法表示运行SpringBoot的引导类 */
        SpringApplication.run(AppRunner.class, args);
    }

}
