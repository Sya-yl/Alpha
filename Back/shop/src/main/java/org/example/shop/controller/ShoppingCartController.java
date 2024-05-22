package org.example.shop.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.jdbc.Null;
import org.example.shop.entity.ShoppingCart;
import org.example.shop.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin()
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
    public List<ShoppingCart>  getCartById(@PathVariable("id") Integer id) {
        return shoppingCartMapper.findByUserId(id);
    }

    // 添加新的购物车项目
    @PostMapping("/shoppingcart")//修改
    public boolean addCart(@RequestBody ShoppingCart cart) {
        //通过userid查找用户名下的购物车表
        List<ShoppingCart> usercart  = shoppingCartMapper.findByUserId(cart.userid);
        //在获取的cart表中对productid进行查找
        boolean found = false;
        for (ShoppingCart item : usercart) {
            if (item.getProductId().equals(cart.getProductId())) {
                // 如果购物车中已经存在相同的产品，则更新该产品的数量
                item.setQuantity(item.getQuantity() + cart.getQuantity());
                shoppingCartMapper.updateCartItem(item);
                found = true;
                break;
            }
        }
        if (!found) {
            // 如果购物车中不存在相同的产品，则将该产品添加到购物车中
            shoppingCartMapper.insert(cart);
            return true;
        }

        return found; //以found作为标记操作成败的标记
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
    public boolean deleteCartById(@RequestBody ShoppingCart cart) {
        //通过userid查找用户名下的购物车表
        List<ShoppingCart> usercart  = shoppingCartMapper.findByUserId(cart.userid);
        //在获取的cart表中对productid进行查找
        boolean found = false;
        //判断是否存在有查找的购物车表项
        if(usercart==null)
            return found;
        for (ShoppingCart item : usercart) {
            if (item.getProductId().equals(cart.getProductId())) {
                // 如果购物车中已经存在相同的产品，则更新该产品的数量
                item.setQuantity(item.getQuantity() - cart.getQuantity());
                shoppingCartMapper.updateCartItem(item);
                if (item.quantity<=0)//若更新后的数量小于等于0，删除该表项
                    shoppingCartMapper.deleteByUserIdAndProductId(item.userid,item.productid);
                found = true;
                break;
            }
        }
        return found;
    }
}
