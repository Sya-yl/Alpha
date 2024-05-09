package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("product")
public class Product {
    private Integer id;
    private String name;
    // 其他属性

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @TableId(value = "productid", type = IdType.AUTO)
    private Integer productId; // 商品ID

    @TableField("productname")
    private String productName; // 商品名称

    private String description; // 商品描述

    private Double price; // 商品价格

    @TableField("categoryid")
    private Integer categoryId; // 分类ID，外键关联到category表的id字段

    private String image; // 商品图片URL


    public Integer getProductId() {
        return productId;
    }
}
