package per.study.listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

/**
 * SpringBoot lifetime Listener
 * <p>
 * Listener 先从 "META-INF/spring.factories" 读
 * 1.引导：利用 BootstrapContext 引导整个项目启动
 * starting: 应用开始，只要有了 BootstrapContext 就直接调用
 * environmentPrepared: 环境准备好（把启动参数等绑定到环境变量中），但IOC还没有创建 【调用一次】
 * <p>
 * 2.启动
 * contextPrepared: IOC容器创建并准备好，但sources（主配置类）还没有加载；组件都创建 【调用一次】
 * contextLoaded: ICO容器加载，主配置类加载进去了，但是IOC容器还没刷新（bean没有创建）
 * started: IOC容器刷新了（所有Bean造好了），但是runner没有调用
 * ready: runner 调用完成
 * 3.运行
 *
 */
public class MyAppListener implements SpringApplicationRunListener {

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("===starting===");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        System.out.println("===environmentPrepared===");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("===contextPrepared===IOC容器准备完成");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("===contextLoaded===IOC容器加载");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("===started===应用启动完成");
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("===ready===准备就绪");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("===failed===准备就绪");
    }
}
