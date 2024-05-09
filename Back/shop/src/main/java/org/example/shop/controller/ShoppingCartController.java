package org.example.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.shop.entity.ShoppingCart;
import org.example.shop.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private final ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
    }

    // 获取全部购物车项目
    @GetMapping("/shoppingcart")
    public List<ShoppingCart> getAllCarts() {
        return shoppingCartMapper.selectList(null);
    }

    // 根据userID获取单个购物车项目
    @GetMapping("/shoppingcart/{id}")
    public ShoppingCart getCartById(@PathVariable("id") Integer id) {
        return shoppingCartMapper.selectById(id);
    }

    // 添加新的购物车项目
    @PostMapping("/shoppingcart")
    public void addCart(@RequestBody ShoppingCart cart) {
        shoppingCartMapper.insert(cart);
    }

    // 更新购物车项目
    @PutMapping("/shoppingcart/{id}")
    public void updateCart(@PathVariable("id") Integer id, @RequestBody ShoppingCart cart) {
        //整个列表更新，在前端处进行增删改
        cart.setCartId(id);
        shoppingCartMapper.updateById(cart);
    }

    // 删除购物车项目
    @DeleteMapping("/{id}")
    public void deleteCartById(@PathVariable("id") Integer id) {
        shoppingCartMapper.deleteById(id);
    }
}
