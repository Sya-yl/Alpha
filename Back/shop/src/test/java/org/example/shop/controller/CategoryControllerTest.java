package org.example.shop.controller;

import org.example.shop.entity.Category;
import org.example.shop.mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryController categoryController;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setId(1); // 假设我们有一个已知的ID来测试
        // 其他字段可以根据需要设置
    }

    @Test
    public void testCreateCategory() {
        // 调用创建类别的方法
        categoryController.createCategory(category);

        // 验证CategoryMapper的insert方法是否被调用，并传入正确的Category对象
        verify(categoryMapper, times(1)).insert(category);
    }

    @Test
    public void testGetCategoryById() {
        // 模拟从mapper中根据ID获取Category
        when(categoryMapper.selectById(1)).thenReturn(category);

        // 调用根据ID获取类别的方法
        Category result = categoryController.getCategoryById(1);


        // 验证CategoryMapper的selectById方法是否被调用，并传入正确的ID
        verify(categoryMapper, times(1)).selectById(1);
    }

    @Test
    public void testUpdateCategory() {
        // 假设我们更新的是已创建的category对象
        // 注意：在实际应用中，你通常不会通过控制器来设置ID，但这里是为了测试目的
        // 调用更新类别的方法
        categoryController.updateCategory(category.getId(), category);

        // 验证CategoryMapper的updateById方法是否被调用，并传入正确的Category对象
        verify(categoryMapper, times(1)).updateById(category);
    }

    @Test
    public void testDeleteCategory() {
        // 调用删除类别的方法
        categoryController.deleteCategory(1);

        // 验证CategoryMapper的deleteById方法是否被调用，并传入正确的ID
        verify(categoryMapper, times(1)).deleteById(1);
    }
}