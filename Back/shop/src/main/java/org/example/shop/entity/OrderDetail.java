package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("orderdetail")
public class OrderDetail {
    @TableId(value = "orderdetailid", type = IdType.AUTO)
    private Integer orderDetailId; // 订单详情ID

    @TableField("orderid")
    private Integer orderId; // 订单ID，外键关联到orders表的orderid字段

    @TableField("productid")
    private Integer productId; // 商品ID，外键关联到product表的productid字段

    @TableField("quantity")
    private Integer quantity; // 商品数量

    @TableField("unitprice")
    private Double unitPrice; // 商品单价
}
