package org.example.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.shop.entity.User;
import org.example.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user/{id}")
    public boolean login(@PathVariable int id, String password) {  //login
        // 创建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();//id 对应
        queryWrapper.eq("id", id).eq("password", password);//password 对应

        // 执行查询
        List<User> userList = userMapper.selectList(queryWrapper);

        // 表不为空，则放行
        return !userList.isEmpty();
    }

    @GetMapping("/user")
    public User find(int id) {  //find
        User find_user = userMapper.selectById(id);

        return find_user;
    }

    @PostMapping("/user")
    public String register(User user) {  //注册新用户
        //必须传输permissions标记用户权限
        int i = userMapper.insert(user);
        if (i > 0) {
            return "Success";
        } else return "Fail";
    }

    @DeleteMapping("user/{id}")
    public String delete(@PathVariable int id) {   //delete
        int i = userMapper.deleteById(id);
        if (i > 0) {
            return "Success";
        } else return "Fail";
    }

    @PutMapping("/user")
    public boolean find(int id, User user) {  //modify
        //  user为前端传递的新数据体(数据一定要全，不能丢失！！！！！！)，id为旧数据用以定位
        // 设置新数据为旧id
        user.setId(id);
        // 更新数据库中的用户信息
        int rows = userMapper.updateById(user);
        // 如果更新成功，返回 true；否则返回 false
        return rows > 0;
    }
}

