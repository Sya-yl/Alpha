package org.example.shop.controller;

import org.example.shop.entity.orders;
import org.example.shop.entity.worker;
import org.example.shop.mapper.workerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin()
@RestController
public class workerController {
    private final org.example.shop.mapper.workerMapper workerMapper;

    @Autowired
    public workerController(workerMapper workerMapper) {
        this.workerMapper = workerMapper;
    }

    @PostMapping("/worker/login/{id}")
    public boolean login(@PathVariable int id, @RequestBody String password) {  //登录
        worker worker = workerMapper.selectById(id);
        return Objects.equals(worker.wpassword, password);
    }

    @PostMapping("/worker/register")
    public String register(@RequestBody worker worker) {  //注册
        int i = workerMapper.insert(worker);
        if (i > 0) {
            return "Success";
        } else return "Fail";
    }

    @DeleteMapping("/worker/delete/{id}")
    public String delete(@PathVariable int id) {   //注销
        int i = workerMapper.deleteById(id);
        if (i > 0) {
            return "Success";
        } else return "Fail";
    }

    @PutMapping("/worker/modify")
    public boolean modify(@RequestBody worker worker) {  //修改用户信息
        //  worker为前端传递的新类，id用以定位
        // 更新数据库中的用户信息
        int rows = workerMapper.updateById(worker);
        // 如果更新成功，返回 true；否则返回 false
        return rows > 0;
    }

    @GetMapping("/worker/work/{wid}")
    public List<orders> work(@PathVariable int wid) {
        return workerMapper.getByWid(wid);
    }
}
