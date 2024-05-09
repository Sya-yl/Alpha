package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;

@TableName("orders")
public class Order {
    @TableId(value = "orderid", type = IdType.AUTO)
    private Integer orderId; // 订单ID

    @TableField("userid")
    private Integer userId; // 用户ID，外键关联到user表的id字段

    @TableField("orderstatus")
    private String orderStatus; // 订单状态

    @TableField("totalamount")
    private Double totalAmount; // 订单总金额

    @TableField("ordertime")
    private Timestamp orderTime; // 下单时间，默认为当前时间戳
}
