package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.shop.entity.ShoppingCart;

import java.util.List;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
    @Select("select * from ShoppingCart where userid = #{userid}")
    public List<ShoppingCart> findByUserId(@Param("userid") Integer userId);

    @Update("UPDATE ShoppingCart SET quantity = #{item.quantity} WHERE userid = #{item.userId} AND productid = #{item.productId}")
    void updateCartItem(@Param("item") ShoppingCart item);

    @Delete("DELETE FROM ShoppingCart WHERE userid = #{userId} AND productid = #{productId}")
    void deleteByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);
}
