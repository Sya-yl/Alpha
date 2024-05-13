package org.example.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.shop.entity.Product;
import org.example.shop.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; // 模拟HTTP请求和响应

    @MockBean
    private ProductMapper productMapper; // 模拟ProductMapper

    @InjectMocks
    private ProductController productController; // 需要测试的控制器
    private WebApplicationContext webApplicationContext;
    private Product product1;
    private Product product2;
    @BeforeEach
    void setUp() {
        // 初始化测试数据
        product1 = new Product();
        product1.setProductId(1);
        product1.setProductName("Product1");
        product1.setPrice(100.00);
        product2 = new Product();
        product2.setProductId(2);
        product2.setProductName("Product2");
        product2.setPrice(200.00);
        // 模拟selectAll返回值
        when(productMapper.selectList(null)).thenReturn(Arrays.asList(product1, product2));
        // 模拟selectById返回值
        when(productMapper.selectById(any(Integer.class))).thenReturn(product1);
        // 模拟insert操作
        doNothing().when(productMapper).insert(any(Product.class));
        // 模拟updateById操作
        doNothing().when(productMapper).updateById(any(Product.class));
        // 模拟deleteById操作
        doNothing().when(productMapper).deleteById(any(Integer.class));
    }

    @Test
    void getAllProducts() throws Exception {
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

//    @Test
//    void getProductById() throws Exception {
//        mockMvc.perform(get("/product/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Product1")));
//    }

//    @Test
//    void getProductsByCategory() throws Exception {
//        Integer categoryId = 1;
//        when(productMapper.selectList(any(QueryWrapper.class))).thenReturn(Arrays.asList(product1, product2));
//        mockMvc.perform(get("/product/{categoryId}", categoryId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)));andExpect(jsonPath("$", hasSize(2)));
//    }

    @Test
    void addProduct() throws Exception {
        Product newProduct = new Product();
        newProduct.setProductName("NewProduct");
        newProduct.setPrice(50.00);

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        mockMvc.perform(post("/product")
                        .contentType("application/json")
                        .content("{\"productName\":\"NewProduct\", \"price\":50.00}"))
                .andExpect(status().isOk());

        verify(productMapper).insert(captor.capture());
        Product capturedProduct = captor.getValue();
        assertEquals("NewProduct", capturedProduct.getProductName());
        assertEquals(50.00, capturedProduct.getPrice());
    }

    @Test
    void updateProduct() throws Exception {
        Product updatedProduct = new Product();
        updatedProduct.setProductId(1);
        updatedProduct.setProductName("UpdatedProduct1");
        updatedProduct.setPrice(150.00);
        mockMvc.perform(put("/product/1")
                        .contentType("application/json")
                        .content("{\"productId\":1, \"productName\":\"UpdatedProduct1\", \"price\":150.00}"))
                .andExpect(status().isOk());
        verify(productMapper).updateById(updatedProduct);
    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isOk());
        verify(productMapper).deleteById(1);
    }
}