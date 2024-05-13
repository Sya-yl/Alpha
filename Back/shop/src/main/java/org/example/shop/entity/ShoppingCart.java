package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("shoppingcart")
public class ShoppingCart {
    private Integer cartId;
    private Integer userId;
    private Integer productId;
    private Integer quantity;

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public void setUserId(Integer userId) { this.userId = userId;}

    public Integer getCartId() {
        return cartId;
    }

    public Integer getUserId() {
        return userId;
    }
}
