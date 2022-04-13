package per.study.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("per.study")
@EnableAspectJAutoProxy
public class SpringConfiguration {
}
