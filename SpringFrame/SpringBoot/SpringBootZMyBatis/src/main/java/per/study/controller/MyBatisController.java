package per.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import per.study.domain.User;
import per.study.mapper.UserMapper;

import java.util.List;

public class MyBatisController {

    @Autowired
    private UserMapper userMapper;

    public List<User> queryAllUser() {
        return userMapper.queryList();
    }

}
