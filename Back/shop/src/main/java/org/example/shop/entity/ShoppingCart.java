package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("shoppingcart")
public class ShoppingCart {
    private Integer cartId;
    private Integer userId;
    private Integer productId;
    private Integer quantity;

    public ShoppingCart(Integer cartId, Integer userId, Integer productId, Integer quantity) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
