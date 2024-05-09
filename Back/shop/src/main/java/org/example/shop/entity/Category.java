package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("category")
public class Category {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; // 分类ID
    private String name;// 分类名称

    public void setId(Integer id) {
        this.id = id;
    }
}
