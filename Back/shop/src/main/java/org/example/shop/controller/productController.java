package org.example.shop.controller;

import org.example.shop.entity.inventory;
import org.example.shop.entity.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
public class productController {
    private org.example.shop.mapper.productMapper productMapper;
    private org.example.shop.mapper.inventoryMapper inventoryMapper;

    @Autowired
    public productController(org.example.shop.mapper.productMapper productMapper,org.example.shop.mapper.inventoryMapper inventoryMapper) {
        this.productMapper = productMapper;
        this.inventoryMapper = inventoryMapper;
    }

    // 获取全部产品
    @GetMapping("/product")
    public List<product> getAllProducts() {
        return productMapper.selectList(null);
    }

    // 根据产品ID获取单个产品
    @GetMapping("/product/{id}")
    public product getProductById(@PathVariable("id") Integer id) {
        return productMapper.selectById(id);
    }

    // 添加新产品
    @PostMapping("/product/new/{sid}/{amount}")
    public String addProduct(@PathVariable("sid") Integer sid,@PathVariable("amount") Integer i,@RequestBody product product) {
        if (!inventoryMapper.getSid(sid))// 检索是否有该超市
            return "This shop is not exist!";
        inventory new_i = new inventory();
        new_i.setAmount(i);
        new_i.setSid(sid);
        new_i.setPid(product.getPid());
        if(!productMapper.insertProduct(product))
            return "Insert product failed!";
        if(!inventoryMapper.addItem(new_i))
            return "Update inventory failed!";
        return "Insert product successful!";
    }

    // 更新产品
    @PutMapping("/product/update")
    public String updateProduct(@RequestBody product product) {
        if(productMapper.updateByPid(product))
            return "Update product successful!";
        else return "Update product failed!";
    }

    // 删除产品
    @DeleteMapping("/product/delete/{id}")
    public String  deleteProduct(@PathVariable("id") Integer id) {
        if(productMapper.deleteById(id)<=0)
            return "Product exist in orders!";
        if (inventoryMapper.deleteById(id)<=0)
            return "Delete product failed!";
        else return "Delete product successful!";
    }
}
