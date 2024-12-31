package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.shop.entity.orders;

import java.util.List;

@Mapper
public interface ordersMapper extends BaseMapper<orders> {
    @Insert("insert into orders(oid, sid, uid, pid, wid, pquantity) values (#{oid},#{sid},#{uid},#{pid},#{wid},#{pquantity})")
    boolean orderInsert(orders order);

    @Select("select * from orders where uid = #{uid}")
    List<orders> getOrdersByUid(int uid);

    @Select("select * from orders where oid = #{oid}")
    List<orders> SelectById(int oid);

    @Delete("delete from orders where oid = #{oid}")
    boolean deleteByoId(int oid);

    @Select("select * from orders where oid = #{oid} and uid = #{uid} and pid =#{pid} and sid = #{sid}")
    orders FindById(orders order);// 确保有该订单项

    @Delete("delete from orders where pid=#{pid} and oid=#{oid}")
    boolean deleteByOidAndPid(orders order);

    @Update("update orders set pquantity = #{pquantity} where oid = #{oid} and uid = #{uid} and pid =#{pid} and sid = #{sid}")
    boolean updateByOidAndPid(orders order);

    @Delete("delete from shoppingcart where uid = #{uid} and pid = #{pid}")
    boolean deleteCart(String uid,int pid);
}
