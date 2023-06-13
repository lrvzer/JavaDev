package per.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import per.study.component.MyYamlHttpMessageConverter;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * 配置静态资源
             * @param registry
             */
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**")
                        .addResourceLocations("classpath:/a/", "classpath:/b/")
                        .setCacheControl(CacheControl.maxAge(7200, TimeUnit.SECONDS))
                ;
            }

            /**
             * 配置拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {

            }

            /**
             * 配置一个能把对象转为yaml的messageConverter
             * @param converters initially an empty list of converters
             */
            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new MyYamlHttpMessageConverter());
            }
        };
    }
}

// 配置方法一
// @EnableWebMvc // 会禁用SpringBoot默认配置
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 保留以前
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//        // 自定义
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/a/", "classpath:/b/")
//                .setCacheControl(CacheControl.maxAge(7200, TimeUnit.SECONDS))
//        ;
//    }
//}
