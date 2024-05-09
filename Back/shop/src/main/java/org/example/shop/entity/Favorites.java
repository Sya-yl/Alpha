package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;

@TableName("favorites")
public class Favorites {
    private Integer favoriteId;
    private Integer userId;
    private Integer productId;
    private Timestamp favoriteTime;
}
