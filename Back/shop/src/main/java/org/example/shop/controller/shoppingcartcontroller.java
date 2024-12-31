package org.example.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.shop.entity.shoppingcart;
import org.example.shop.mapper.shoppingcartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
public class shoppingcartcontroller {
    private final shoppingcartMapper shoppingcartMapper;

    @Autowired
    public shoppingcartcontroller(shoppingcartMapper shoppingcartMapper) {
        this.shoppingcartMapper = shoppingcartMapper;
    }

    // 根据ID获取购物车项目
    @GetMapping("/shoppingcart/{uid}/{cartid}")
    public List<shoppingcart> getCartById(@PathVariable("uid") String uid, @PathVariable("cartid") Integer cartid) {
        return shoppingcartMapper.findById(uid, cartid);
    }

    // 添加新的购物车项目
    @PostMapping("/shoppingcart/update")//修改
    public String addCart(@RequestBody shoppingcart cart) {
        //通过userid查找用户名下的购物车
        shoppingcart usercart = shoppingcartMapper.FindByUidAndPid(cart.uid, cart.pid);
        //对pid进行查找
        if (usercart != null) {
            // 如果购物车中已经存在相同的产品，则更新该产品的数量
            cart.setQuantity(usercart.getQuantity() + cart.getQuantity());
            if (!shoppingcartMapper.updateCartItem(cart))
                return "fail";
        }
        else {
            // 没有该项
            if (!shoppingcartMapper.insertItem(cart))
                return "fail";
        }
        return "success";
    }

    // 更新购物车项目
    @PutMapping("/shoppingcart")
    public String updateCart(@RequestBody shoppingcart cart) {
        Integer cartId = cart.getCartid();
        String userId = cart.getUid();

        // 检查 cartId 和 userId 是否有效
        if (cartId == null || userId == null) {
            // 处理无效数据的情况，可以抛出异常或者返回错误信息
            return "ERROR";
        }

        // 查找特定 cartId 和 userId 对应的购物车数据
        QueryWrapper<shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cartId", cartId);
        queryWrapper.eq("userId", userId);
        shoppingcart existingCart = shoppingcartMapper.selectOne(queryWrapper);

        if (existingCart == null) {
            // 如果购物车数据不存在，可以根据业务逻辑选择是创建新的购物车数据还是返回错误信息
            return "ERROR";
        }
        cart.setCartid(cartId);
        int rows = shoppingcartMapper.updateById(cart);
        return rows > 0 ? "Successes" : "ERROR";
    }

    // 删除购物车项目
    @DeleteMapping("/shoppingcart/delete/{id}")
    public boolean deleteCartById(@RequestBody shoppingcart cart) {
        //通过userid查找用户名下的购物车表
        shoppingcart user_cart = shoppingcartMapper.FindByUidAndPid(cart.uid, cart.pid);
        if (user_cart == null)
            return false;
        else{
            return shoppingcartMapper.deleteByUserIdAndProductId(cart.uid, cart.pid);
        }
    }
}
