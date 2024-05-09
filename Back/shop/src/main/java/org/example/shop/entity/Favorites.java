package org.example.shop.entity;

import java.sql.Timestamp;

public class Favorites {
    private Integer favoriteId;
    private Integer userId;
    private Integer productId;
    private Timestamp favoriteTime;

    // 构造函数
    public Favorites() {}

    // Getter 和 Setter 方法
    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
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

    public Timestamp getFavoriteTime() {
        return favoriteTime;
    }

    public void setFavoriteTime(Timestamp favoriteTime) {
        this.favoriteTime = favoriteTime;
    }
}
