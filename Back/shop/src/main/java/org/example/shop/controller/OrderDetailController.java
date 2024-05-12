package org.example.shop.controller;

import org.example.shop.entity.OrderDetail;
import org.example.shop.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @PostMapping("/orderdetails")
    public boolean insertOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailMapper.insert(orderDetail) > 0;
    }

    @GetMapping("/orderdetails/{id}")
    public OrderDetail getOrderDetailById(@PathVariable("id") Integer id) {
        return orderDetailMapper.selectById(id);
    }

    @PutMapping("/orderdetails")
    public boolean updateOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailMapper.updateById(orderDetail) > 0;
    }

    @DeleteMapping("/orderdetails/{id}")
    public boolean deleteOrderDetail(@PathVariable("id") Integer id) {
        return orderDetailMapper.deleteById(id) > 0;
    }
}
