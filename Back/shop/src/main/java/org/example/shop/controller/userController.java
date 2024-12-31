package org.example.shop.controller;

import org.example.shop.entity.shoppingcart;
import org.example.shop.entity.user;
import org.example.shop.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin()
@RestController
public class userController {

    @Autowired
    private org.example.shop.mapper.userMapper userMapper;

    @PostMapping("/user/login/{id}")
    public int login(@PathVariable String id, @RequestBody String password) {  //登录
        user user = userMapper.selectById(id);
        if (user != null)
            if (Objects.equals(user.upassword, password))
                if (user.permisson==1 || user.permisson==0)
                    return user.permisson;
        return 2;
    }

    @PostMapping("/user/register")
    public boolean register(@RequestBody user user) {  //注册新用户
        if(!userMapper.user_insert(user)) return false;
        shoppingcart cart = new shoppingcart();
        cart.cartid = Integer.valueOf(user.uid);
        cart.uid=user.uid;
        cart.pid = 0;// 意味着购物车为空
        return userMapper.shoppppingcart_insert(cart);
    }

    @DeleteMapping("/user/delete/{id}")
    public String delete(@PathVariable int id) {   //注销
        int i = userMapper.deleteById(id);
        if (i > 0) {
            return "Success";
        } else return "Fail";
    }

    @PutMapping("/user/modify")
    public boolean modify(@RequestBody user user) {  //修改用户信息
        //  user为前端传递的新类，id用以定位
        // 更新数据库中的用户信息
        int rows = userMapper.updateById(user);
        // 如果更新成功，返回 true；否则返回 false
        return rows > 0;
    }
}

