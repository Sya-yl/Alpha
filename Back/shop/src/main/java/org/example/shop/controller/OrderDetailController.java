package org.example.shop.controller;

import org.example.shop.entity.OrderDetail;
import org.example.shop.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @PostMapping
    public boolean saveOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailMapper.insert(orderDetail) > 0;
    }

    @GetMapping("/{id}")
    public OrderDetail getOrderDetailById(@PathVariable("id") Integer id) {
        return orderDetailMapper.selectById(id);
    }

    @PutMapping
    public boolean updateOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailMapper.updateById(orderDetail) > 0;
    }

    @DeleteMapping("/{id}")
    public boolean deleteOrderDetail(@PathVariable("id") Integer id) {
        return orderDetailMapper.deleteById(id) > 0;
    }
}
