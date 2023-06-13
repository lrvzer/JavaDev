package per.study;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoreAppRunner {
    public static void main(String[] args) {
        SpringApplication.run(CoreAppRunner.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> System.out.println("ApplicationRunner ===");
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> System.out.println("CommandLineRunner ===");
    }
}