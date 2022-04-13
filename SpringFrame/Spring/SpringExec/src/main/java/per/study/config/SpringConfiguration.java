package per.study.config;

import org.springframework.context.annotation.*;


//@Configuration
@Import(JDBCConfig.class)
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = "per.study")
@EnableAspectJAutoProxy
public class SpringConfiguration
{
}
