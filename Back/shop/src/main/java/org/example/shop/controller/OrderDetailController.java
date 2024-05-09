package org.example.shop.controller;

import org.example.shop.entity.OrderDetail;
import org.example.shop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public boolean saveOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.save(orderDetail);
    }

    @GetMapping("/{id}")
    public OrderDetail getOrderDetailById(@PathVariable("id") Integer id) {
        return orderDetailService.getById(id);
    }

    @PutMapping
    public OrderDetail updateOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailService.updateById(orderDetail);
        return orderDetail;
    }

    @DeleteMapping("/{id}")
    public void deleteOrderDetail(@PathVariable("id") Integer id) {
        orderDetailService.removeById(id);
    }
}
