package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.shop.entity.inventory;
import org.example.shop.entity.shop;

import java.util.List;

@Mapper
public interface inventoryMapper extends BaseMapper<inventory> {
    @Select("select * from inventory")
    List<inventory> getAllItems();

    @Select("select * from inventory where pid = #{pid}")
    List<inventory> getItemById(int pid);

    @Insert("insert into inventory values (#{pid},#{sid},#{amount})")
    boolean addItem(inventory inventory);

    @Update("update inventory set amount = #{amount} where sid = #{sid} and pid = #{pid}")
    boolean updateItem(inventory inventory);

    @Delete("delete from inventory where sid = #{sid} and pid = #{pid}")
    boolean deleteItem(inventory inventory);

    @Select("select * from inventory where pid = #{pid} and sid = #{sid}")
    inventory getBySidAndPid(inventory inventory);

    @Select("select * from shop where sid = #{sid}")
    boolean getSid(int sid);// 仅检索是否有该id的超市
}
