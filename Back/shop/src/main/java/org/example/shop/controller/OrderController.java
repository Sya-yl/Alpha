package org.example.shop.controller;

import org.example.shop.entity.Order;
import org.example.shop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/order")
    public boolean insertOrder(@RequestBody Order order) {// insert
        return orderMapper.insert(order) > 0;
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable("id") Integer id) {
        return orderMapper.selectById(id);
    }

    @PutMapping("/order")
    public boolean updateOrder(@RequestBody Order order) {// update
        return orderMapper.updateById(order) > 0;
    }

    @DeleteMapping("/order/{id}")
    public boolean deleteOrder(@PathVariable("id") Integer id) {
        return orderMapper.deleteById(id) > 0;
    }
}
