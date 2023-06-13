package per.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.study.bean.TUser;
import per.study.mapper.UserMapper;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user/{id}")
    public TUser getUser(@PathVariable("id") Long id) {
        return userMapper.getUserById(id);
    }
}
