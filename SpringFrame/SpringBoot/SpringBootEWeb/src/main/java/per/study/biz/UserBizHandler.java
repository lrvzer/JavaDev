package per.study.biz;

import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import per.study.entity.Person;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 专门处理User有关的业务
 */
@Slf4j
@Component
public class UserBizHandler {
    /**
     * 查询指定id的用户
     *
     * @param request
     * @return
     */
    public ServerResponse getUser(ServerRequest request) {
        log.info("查询[{}]用户信息完成", request.pathVariable("id"));

        // 业务处理
        Person person = new Person(1L, "zhangSan", 18, "admin@qq.com");
        // 构造相应
        return ServerResponse.ok().body(person);
    }

    public ServerResponse getUsers(ServerRequest request) {
        log.info("查询所有用户信息完成");
        // 业务处理
        List<Person> list = Arrays.asList(new Person(1L, "zhangSan", 18, "admin@qq.com"),
                new Person(2L, "liSi", 19, "admin2@qq.com"));
        // 构造相应
        return ServerResponse.ok().body(list);
    }

    public ServerResponse saveUser(ServerRequest request) throws ServletException, IOException {
        Person person = request.body(Person.class);
        log.info("保存用户信息完成 {}", person);
        return ServerResponse.ok().build();
    }

    public ServerResponse updateUser(ServerRequest request) {
        log.info("更新用户信息完成");
        return ServerResponse.ok().build();
    }

    public ServerResponse deleteUser(ServerRequest request) {
        log.info("删除用户信息完成");
        return ServerResponse.ok().build();
    }


}
