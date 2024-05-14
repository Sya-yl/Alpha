package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("shoppingcart")
public class ShoppingCart {
    private Integer cartid;
    public Integer userid;
    public Integer productid;
    public Integer quantity;

    public void setCartId(Integer cartId) {
        this.cartid = cartId;
    }

    public Integer getCartId() {
        return cartid;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userId) {
        this.userid = userId;
    }

    public Integer getProductId() {
        return productid;
    }

    public void setProductId(Integer productId) {
        this.productid = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
