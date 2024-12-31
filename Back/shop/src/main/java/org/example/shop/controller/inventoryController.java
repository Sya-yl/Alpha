package org.example.shop.controller;

import org.example.shop.entity.inventory;
import org.example.shop.mapper.inventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin()
@RestController
@RequestMapping("/category")
public class inventoryController {
    @Autowired
    private inventoryMapper inventoryMapper;

    // 获取所有库存项
    @GetMapping("/inventory/all")
    public List<inventory> getAllItems() {
        return inventoryMapper.getAllItems();
    }

    // 根据pID获取库存项
    @GetMapping("/inventory/{pid}")
    public List<inventory> getItemById(@PathVariable int pid) {
        return inventoryMapper.getItemById(pid);
    }

    // 添加库存项
    @PostMapping("/inventory/add")
    public String addItem(@RequestBody inventory inventory) {
        boolean result = inventoryMapper.addItem(inventory);
        if (result) {
            return "Item added successfully!";
        } else {
            return "Failed to add item.";
        }
    }

    // 更新库存项
    @PutMapping("/update")
    public String updateItem(@RequestBody inventory inventory) {
        boolean result;
        if (inventory.amount > 0){
            result = inventoryMapper.updateItem(inventory);
        }
        else{
            result = inventoryMapper.deleteItem(inventory);
        }
        if (result) {
            return "Item updated successfully!";
        } else {
            return "Failed to update item.";
        }
    }
}
