package per.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;
import per.study.biz.UserBizHandler;

@Configuration
public class WebFunction {

    /**
     * 函数式Web
     * 1.给容器中放一个Bean：类型是RouterFunction<ServerResponse>
     * <p>
     * 核心四大接口
     * 1、RouterFunction：定义路由信息，发什么请求，谁来处理
     * 2、RequestPredicate：定义请求，请求谓语。请求方式（GET、POST）、请求参数
     * 3、ServerRequest：封装请求完整数据
     * 4、ServerResponse：封装相应完整数据
     *
     * @param userBizHandler UserBizHandler由@Component修饰，会自动注入
     */
    @Bean
    public RouterFunction<ServerResponse> userRoute(UserBizHandler userBizHandler) {
        // 开始定义路由信息
        return RouterFunctions.route()
                .GET("/user/{id}", RequestPredicates.accept(MediaType.ALL), userBizHandler::getUser)
                .GET("/users", userBizHandler::getUsers)
                .POST("/user", RequestPredicates.accept(MediaType.APPLICATION_JSON), userBizHandler::saveUser)
                .PUT("/user/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON), userBizHandler::updateUser)
                .DELETE("/user/{id}", userBizHandler::getUser)
                .build()
                ;
    }

}
