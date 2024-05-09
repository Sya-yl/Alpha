package org.example.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.shop.entity.Order;

public interface OrderService extends IService<Order> {
    Order findOrderById(int id);
    // 可以添加特定的业务方法
}
