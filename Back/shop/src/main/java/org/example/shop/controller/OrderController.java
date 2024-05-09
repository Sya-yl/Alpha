package org.example.shop.controller;

import org.example.shop.entity.Order;
import org.example.shop.mapper.OrderMapper;
import org.example.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderMapper orderService;

    @PostMapping
    public boolean saveOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Integer id) {
        return orderService.getById(id);
    }

    @PutMapping
    public Order updateOrder(@RequestBody Order order) {
        orderService.updateById(order);
        return order;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) {
        orderService.removeById(id);
    }
}
