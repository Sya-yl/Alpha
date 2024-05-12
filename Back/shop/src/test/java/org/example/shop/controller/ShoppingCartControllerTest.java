package org.example.shop.controller;

import org.example.shop.entity.ShoppingCart;
import org.example.shop.mapper.ShoppingCartMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ShoppingCartControllerTest {

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    private ShoppingCart cart1;
    private ShoppingCart cart2;

    @BeforeEach
    public void setUp() {
        cart1 = new ShoppingCart(1,1,1001,1);
        cart2 = new ShoppingCart(2,1,1002,2);
    }

    @Test
    public void testGetAllCarts() {
        // Arrange
        // 模拟mapper返回包含cart1和cart2的列表
        when(shoppingCartMapper.selectList(null)).thenReturn(Arrays.asList(cart1, cart2));

        // Act
        // 调用controller的getAllCarts方法
        List<ShoppingCart> result = shoppingCartController.getAllCarts();

        // Assert
        // 断言结果不为空，大小为2，且结果列表中的元素与预期一致
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(cart1, result.get(0));
        assertEquals(cart2, result.get(1));
        // 验证mapper的selectList方法被调用了一次
        verify(shoppingCartMapper, times(1)).selectList(null);
    }

//    @Test
//    public void testGetCartById() {
//        // Arrange
//        // 假设我们要获取的购物车ID为1，模拟mapper返回cart1
////        when(shoppingCartMapper.selectByCartId(1)).thenReturn(cart1);
//
//        // Act
//        // 这里假设ShoppingCartController有一个getCartById方法，它接受一个cartId作为参数
//        // ShoppingCart resultCart = shoppingCartController.getCartById(1); // 这里是假设的方法调用
//
//        // 由于我们不知道ShoppingCartController中getCartById的具体实现，这里我们使用一个假设的返回值
//        ShoppingCart resultCart = cart1; // 假设controller直接返回了mapper的结果
//
//        // Assert
//        // 断言返回的购物车与预期的cart1相同
//        assertNotNull(resultCart);
//        assertEquals(cart1.getCartId(), resultCart.getCartId());
//        // 验证mapper的selectByCartId方法被调用了一次，且参数为1
////        verify(shoppingCartMapper, times(1)).selectByCartId(1);
//    }

}