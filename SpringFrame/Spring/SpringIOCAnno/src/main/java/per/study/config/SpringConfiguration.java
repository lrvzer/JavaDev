package per.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration        指定当前类是配置类
 * ComponentScan        指定Spring创建容器时要扫描的包
 *                      属性value/basePackages使用相同，都指定扫描的包名
 * ComponentScans       配置多个ComponentScan
 *
 * Bean                 用于把当前方法的返回值作为bean对象存入Spring容器中
 *                      属性name默认为方法的名称
 *                      当使用注解配置方法时，如果方法中有参数，Spring会去容器中查找有没有可用的bean对象
 *                      查找的方式与Autowired自动注入方式一致
 *
 * Import               用于导入其他的配置类（使用Import的注解之后，有Import注解的类就父配置类，而导入的都是子配置类）
 */
@Configuration
@Import(JDBCConfig.class)
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = "per.study")
public class SpringConfiguration {

}
