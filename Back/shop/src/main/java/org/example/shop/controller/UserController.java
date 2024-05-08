package org.example.shop.controller;

import org.example.shop.entity.User;
import org.example.shop.mapper.UserMapper;
import org.example.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public User login(){
        User login_User = userMapper.find();
        System.out.println(login_User);
        return login_User;
    }

    @PostMapping("/register")
    public String register(User user){
        int i=userMapper.insert(user);
        if(i>0){
            return "Success";
        }
        else return "Fail";
    }
}

