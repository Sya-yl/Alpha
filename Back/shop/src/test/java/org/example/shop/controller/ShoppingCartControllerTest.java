package org.example.shop.controller;

import org.example.shop.entity.ShoppingCart;
import org.example.shop.mapper.ShoppingCartMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(ShoppingCartController.class)
class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartMapper shoppingCartMapper; // 模拟ShoppingCartMapper

    @Captor
    private ArgumentCaptor<ShoppingCart> cartCaptor; // 用于捕获传递给模拟方法的ShoppingCart对象

    @BeforeEach
    public void setUp() {
        // 清除模拟对象之前的状态
        reset(shoppingCartMapper);
    }

    @Test
    public void testGetAllCarts() throws Exception {
        // 模拟getAllCarts返回空列表
        when(shoppingCartMapper.selectList(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/shoppingcart"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetCartById() throws Exception {
        // 创建模拟的购物车对象
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(1);
        cart.setCartId(1);

        // 模拟getCartById返回特定的购物车对象
        when(shoppingCartMapper.selectById(any())).thenReturn(cart);

        mockMvc.perform(MockMvcRequestBuilders.get("/shoppingcart/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddCart() throws Exception {
        // 创建模拟的购物车对象
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(1);

        // 模拟addCart返回成功的结果
        when(shoppingCartMapper.insert(any(ShoppingCart.class))).thenReturn(1);

        // 发起添加购物车的请求
        mockMvc.perform(MockMvcRequestBuilders.post("/shoppingcart")
                        .content("{\"userId\":1}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 验证购物车是否被添加
        verify(shoppingCartMapper).insert(cartCaptor.capture());
        ShoppingCart capturedCart = cartCaptor.getValue();
        assertEquals(1, capturedCart.getUserId().intValue());
    }
}