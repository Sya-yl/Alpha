package org.example.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.shop.entity.User;
import org.example.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin()
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/user/{id}")
    public boolean login(@PathVariable int id, @RequestBody String password) {  //login
        User user = userMapper.selectById(id);
        return Objects.equals(user.password, password);
    }

    @GetMapping("/user/{id}")
    public User find(@PathVariable int id) {  //find
        User find_user = userMapper.selectById(id);

        return find_user;
    }

    @PostMapping("/user")
    public String register(@RequestBody User user) {  //注册新用户
        //必须传输permissions标记用户权限
        if (user.getPermissions() == 0){
            return "Fail: permissions field is required";
        }
        int i = userMapper.insert(user);
        if (i > 0) {
            return "Success";
        } else return "Fail";
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable int id) {   //delete
        int i = userMapper.deleteById(id);
        if (i > 0) {
            return "Success";
        } else return "Fail";
    }

    @PutMapping("/user")
    public boolean modify(@RequestBody User user) {  //modify
        //  user为前端传递的新类，id用以定位
        // 更新数据库中的用户信息
        int rows = userMapper.updateById(user);
        // 如果更新成功，返回 true；否则返回 false
        return rows > 0;
    }
}

