package com.atguigu.redis.controller;

import java.util.Random;

import javax.annotation.Resource;

import com.atguigu.redis.entities.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import com.atguigu.redis.entities.User;
import com.atguigu.redis.service.UserService;

import cn.hutool.core.util.IdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(description = "用户user接口")
@RestController
@Slf4j
public class UserController
{
    @Resource
    private UserService userService;

    @ApiOperation("数据库新增5条记录")
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public void addUser() {
        for (int i = 0; i < 5; ++i) {
            User user = new User();

            user.setUsername("zy" + i);
            user.setPassword(IdUtil.simpleUUID().substring(0, 6));
            user.setSex((byte) new Random().nextInt(2));

            userService.addUser(user);
        }
    }

    @ApiOperation("删除1条记录")
    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.POST)
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @ApiOperation("修改1条记录")
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public void updateUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userService.updateUser(user);
    }

    @ApiOperation("查询1条记录")
    @RequestMapping(value = "/user/find/{id}", method = RequestMethod.POST)
    public User findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

}
