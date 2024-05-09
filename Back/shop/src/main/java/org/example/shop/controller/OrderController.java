package org.example.shop.controller;

import org.example.shop.entity.Order;
import org.example.shop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public boolean saveOrder(@RequestBody Order order) {
        return orderMapper.insert(order) > 0;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Integer id) {
        return orderMapper.selectById(id);
    }

    @PutMapping
    public boolean updateOrder(@RequestBody Order order) {
        return orderMapper.updateById(order) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean deleteOrder(@PathVariable("id") Integer id) {
        return orderMapper.deleteById(id) > 0;
    }
}
