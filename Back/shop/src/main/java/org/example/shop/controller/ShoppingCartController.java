package org.example.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.shop.entity.ShoppingCart;
import org.example.shop.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public boolean addCart(@RequestBody ShoppingCart cart) {
        int rows = shoppingCartMapper.insert(cart);
        return rows>0;
    }

    // 更新购物车项目
    @PutMapping("/shoppingcart")
    public String updateCart(@RequestBody ShoppingCart cart) {
        Integer cartId = cart.getCartId();
        Integer userId = cart.getUserId();

        // 检查 cartId 和 userId 是否有效
        if (cartId == null || userId == null) {
            // 处理无效数据的情况，可以抛出异常或者返回错误信息
            return "ERROR";
        }

        // 查找特定 cartId 和 userId 对应的购物车数据
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cartId", cartId);
        queryWrapper.eq("userId", userId);
        ShoppingCart existingCart = shoppingCartMapper.selectOne(queryWrapper);

        if (existingCart == null) {
            // 如果购物车数据不存在，可以根据业务逻辑选择是创建新的购物车数据还是返回错误信息
            return "ERROR";
        }
        cart.setCartId(cartId);
        int rows = shoppingCartMapper.updateById(cart);
        return rows>0?"Successes":"ERROR";
    }

    // 删除购物车项目
    @DeleteMapping("/shoppingcart/{id}")
    public boolean deleteCartById(@PathVariable("id") Integer id) {
        int rows = shoppingCartMapper.deleteById(id);
        return rows>0;
    }
}
