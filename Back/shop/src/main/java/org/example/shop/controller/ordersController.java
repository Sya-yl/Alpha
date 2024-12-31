package org.example.shop.controller;

import org.example.shop.entity.inventory;
import org.example.shop.entity.orders;
import org.example.shop.mapper.inventoryMapper;
import org.example.shop.mapper.ordersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin()
@RestController
public class ordersController {

    @Autowired
    private ordersMapper ordersMapper;
    @Autowired
    private inventoryMapper inventoryMapper;

    private boolean update_inventory(orders order, int delta) {
        inventory new_inventory = new inventory();
        new_inventory.setPid(order.getPid());
        new_inventory.setSid(order.getSid());
        inventory old = inventoryMapper.getBySidAndPid(new_inventory);
        if (old.getAmount()+delta < 0) return false;
        new_inventory.setAmount(old.getAmount() + delta);
        return inventoryMapper.updateItem(new_inventory);
    }

    // 创建订单
    @PostMapping("/order/new")
    public Boolean createOrder(@RequestBody orders order) {
        // 插入订单
        if (!ordersMapper.orderInsert(order))
            return false;
        if (update_inventory(order, -order.getPquantity())) {
            ordersMapper.deleteCart(order.getUid(),order.getPid());
            return true;
        }
        else{
            ordersMapper.deleteByoId(order.getPid());
            return false;
        }
    }

    // 查询用户订单
    @GetMapping("/order/user/{uid}")
    public List<orders> getOrders(@PathVariable int uid) {
        return ordersMapper.getOrdersByUid(uid);  // 查询所有订单
    }

    // 根据订单ID查询订单
    @GetMapping("/order/search/{oid}")
    public List<orders> getOrderById(@PathVariable Integer oid) {
        return ordersMapper.SelectById(oid);  // 根据ID查询订单
    }

    // 更新订单(在有订单的基础上)
    @PutMapping("/order/update")
    public boolean updateOrder(@RequestBody orders order) {
        orders order_find = ordersMapper.FindById(order);
        if (order_find == null) {
            return false;
        }
        if (order.getPquantity() == 0) {
            if (ordersMapper.deleteByOidAndPid(order))
                return update_inventory(order, order.getPquantity());
        }
        else {
            if (ordersMapper.updateByOidAndPid(order))
                return update_inventory(order, -order.getPquantity());
        }
        return false;
    }

    // 删除订单
    @DeleteMapping("/order/delete/{oid}")
    public boolean deleteOrder(@PathVariable Integer oid) {
        List<orders> orders = ordersMapper.SelectById(oid);
        if (orders != null && !orders.isEmpty()) {
            // 遍历每一个订单项，进行库存更新
            for (orders order : orders) {
                // 获取订单中的商品数量，并进行库存更新
                if(!update_inventory(order, order.getPquantity()))
                    return false;
            }
            return ordersMapper.deleteByoId(oid);
        }
        else return false;
    }
}
