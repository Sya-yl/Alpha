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

    public Integer getCartId() {
        return cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
