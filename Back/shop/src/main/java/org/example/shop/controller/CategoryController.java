package org.example.shop.controller;

import org.example.shop.entity.Category;
import org.example.shop.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;

    // 创建类别
    @PostMapping
    public void createCategory(@RequestBody Category category) {
        categoryMapper.insert(category);
    }

    // 根据ID获取类别
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Integer id) {
        return categoryMapper.selectById(id);
    }

    // 更新类别
    @PutMapping("/{id}")
    public void updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        category.setId(id);
        categoryMapper.updateById(category);
    }

    // 删除类别
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Integer id) {
        categoryMapper.deleteById(id);
    }

    // 获取所有类别
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryMapper.selectList(null);
    }
}
