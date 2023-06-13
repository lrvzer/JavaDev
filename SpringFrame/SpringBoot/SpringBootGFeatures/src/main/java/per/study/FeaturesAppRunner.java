package per.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import per.study.bean.Cat;
import per.study.bean.Dog;
import per.study.bean.Pig;
import per.study.bean.Sheep;

/**
 * 1. 标识环境
 * 1) 区分环境：dev(开发环境)、test(测试环境)、prod(生产环境)
 * 2) 指定每个组件在哪个环境下生效：
 *      default(默认环境，可不需使用@Profile指定)
 *      其他环境激活需要标注@Profile
 *      组件没有标注@Profile代表任意时候都生效
 * 3) 默认只有激活指定的环境，这些组件才会生效
 * <p>
 * 2. 激活环境
 * 配置文件激活：spring.profiles.active=dev
 * 命令行激活：java -jar xx.jar --spring.profiles.active=prod
 * <p>
 * 3. 配置文件使用Profile功能
 * 1) application.properties：主配置文件。任何情况下都生效
 * 2) 其他Profile环境下命名规范： application-{profile标识}.properties
 *      比如：application-dev.properties
 * 3) 优先级：application-{profile标识}.properties > application.properties
 */
@Slf4j
@SpringBootApplication // 标注主程序类
public class FeaturesAppRunner {
    public static void main(String[] args) {
        // 方式一
//        // 1、SpringApplication：Boot应用的核心API入口
//        SpringApplication.run(FeaturesAppRunner.class, args);

        // 方式二
        // 可拆解
//        SpringApplication application = new SpringApplication(FeaturesAppRunner.class);
//        // 程序化调整SpringApplication的参数 【配置文件优先级高于程序化调整的优先级】
//        application.setBannerMode(Banner.Mode.CONSOLE);
//        application.run(args);

        // 方式三：Builder方式构建SpringApplication；通过FluentAPI进行设置
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .main(FeaturesAppRunner.class)
                .sources(FeaturesAppRunner.class)
                .bannerMode(Banner.Mode.CONSOLE)
//                .environment(null)
//                .listeners(null)
                .run(args);

        Cat cat = null;
        try {
            cat = context.getBean(Cat.class);
            log.info("cat: {}", cat);

        } catch (BeansException e) {

        }
        Dog dog = null;
        try {
            dog = context.getBean(Dog.class);
            log.info("dog: {}", dog);

        } catch (BeansException e) {

        }
        Pig pig = null;
        try {
            pig = context.getBean(Pig.class);
            log.info("pig: {}", pig);
        } catch (BeansException e) {

        }
        Sheep sheep = null;
        try {
            sheep = context.getBean(Sheep.class);
            log.info("sheep: {}", sheep);
        } catch (BeansException e) {

        }
    }
}