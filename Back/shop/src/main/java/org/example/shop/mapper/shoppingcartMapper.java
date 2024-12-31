package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.shop.entity.shoppingcart;

import java.util.List;

@Mapper
public interface shoppingcartMapper extends BaseMapper<shoppingcart> {
    @Select("select * from shoppingcart where uid = #{uid} and cartid = #{cartid}")
    List<shoppingcart> findById(String uid,Integer cartid);

    @Update("UPDATE shoppingcart SET quantity = #{item.quantity} WHERE uid = #{item.uid} AND pid = #{item.pid}")
    boolean updateCartItem(@Param("item") shoppingcart item);

    @Delete("DELETE FROM shoppingcart WHERE uid = #{uid} AND pid = #{pid}")
    boolean deleteByUserIdAndProductId(String uid,Integer pid);

    @Select("select * from shoppingcart where pid = #{pid} and uid = #{uid}")
    shoppingcart FindByUidAndPid(String uid,Integer pid);

    @Update("UPDATE shoppingcart SET quantity = #{quantity}, pid = #{pid} WHERE uid = #{uid} AND cartid = #{cartid}")
    boolean updateNewItem(shoppingcart item);

    @Select("select * from shoppingcart where pid is null and uid = #{uid} and cartid = #{cartid} and quantity is null")
    shoppingcart isEmpty(shoppingcart item);

    @Insert("insert into shoppingcart values (#{cartid},#{uid},#{pid},#{quantity})")
    boolean insertItem(shoppingcart item);
}
