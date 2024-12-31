package org.example.shop.controller;

import org.example.shop.entity.shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class shopcontroller {

    @Autowired
    private org.example.shop.mapper.shopMapper shopMapper;

    // 创建商店 (增)
    @PostMapping("/shop/add")
    public String addShop(@RequestBody shop shop) {
        int result = shopMapper.insert(shop);
        if (result > 0) {
            return "Shop added successfully!";
        } else {
            return "Failed to add shop.";
        }
    }

    // 获取所有商店 (查)
    @GetMapping("/shop/all")
    public List<shop> getAllShops() {
        return shopMapper.selectList(null);
    }

    // 获取单个商店 (查)
    @GetMapping("/shop/{id}")
    public shop getShopById(@PathVariable Integer id) {
        return shopMapper.selectById(id);
    }

    // 更新商店信息 (改)
    @PutMapping("/shop/update")
    public String updateShop(@RequestBody shop shop) {
        int result = shopMapper.updateById(shop);
        if (result > 0) {
            return "Shop updated successfully!";
        } else {
            return "Failed to update shop.";
        }
    }

    // 删除商店 (删)
    @DeleteMapping("/shop/delete/{id}")
    public String deleteShop(@PathVariable Integer id) {
        int result = shopMapper.deleteById(id);
        if (result > 0) {
            return "Shop deleted successfully!";
        } else {
            return "Failed to delete shop.";
        }
    }
}
