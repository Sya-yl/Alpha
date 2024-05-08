package org.example.shop.controller;

import org.example.shop.entity.Product;
import org.example.shop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    // 获取所有产品列表
    @GetMapping("/products")
    public List<Product> listProducts() {
        return productMapper.selectList(null); // 无条件查询全部
    }

    // 根据 ID 查询单个产品
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") int id) {
        return productMapper.selectById(id);
    }

    // 添加一个新产品
    @PostMapping("/product")
    public String addProduct(@RequestBody Product product) {
        int result = productMapper.insert(product);
        return result > 0 ? "Success" : "Fail";
    }

    // 更新产品信息
    @PutMapping("/product/{id}")
    public String updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        product.setProductId(id);  // 确保ID一致
        int result = productMapper.updateById(product);
        return result > 0 ? "Success" : "Fail";
    }

    // 删除产品
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        int result = productMapper.deleteById(id);
        return result > 0 ? "Success" : "Fail";
    }
}
