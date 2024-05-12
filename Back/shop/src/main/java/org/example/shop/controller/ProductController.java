package org.example.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.shop.entity.Product;
import org.example.shop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
public class ProductController {
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    // 获取全部产品
    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return productMapper.selectList(null);
    }

    // 根据产品ID获取单个产品--“序列号”
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productMapper.selectById(id);
    }

    // 根据分类ID获取产品列表
    @GetMapping("/product/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable("categoryId") Integer categoryId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        return productMapper.selectList(queryWrapper);
    }

    // 添加新产品
    @PostMapping("/product")
    public boolean addProduct(@RequestBody Product product) {
        int rows = productMapper.insert(product);
        return rows>0;
    }

    // 更新产品
    @PutMapping("/product/{id}")
    public boolean updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        product.setId(id);
        int rows = productMapper.updateById(product);
        return rows>0;
    }

    // 删除产品
    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable("id") Integer id) {
        int rows = productMapper.deleteById(id);
        return rows>0;
    }
}
