package org.example.shop.controller;

import org.example.shop.entity.Category;
import org.example.shop.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;

    // 创建类别
    @PostMapping("/category")
    public void createCategory(@RequestBody Category category) {
        categoryMapper.insert(category);
    }

    // 根据ID获取类别
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable("id") Integer id) {
        return categoryMapper.selectById(id);
    }

    // 更新类别
    @PutMapping("/category/{id}")
    public void updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        category.setId(id);
        categoryMapper.updateById(category);
    }

    // 删除类别
    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable("id") Integer id) {
        categoryMapper.deleteById(id);
    }

    // 获取所有类别
    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryMapper.selectList(null);
    }
}
