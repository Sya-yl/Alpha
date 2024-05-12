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

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
