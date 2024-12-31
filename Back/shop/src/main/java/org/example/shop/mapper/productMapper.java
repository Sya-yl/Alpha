package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.example.shop.entity.product;

@Mapper
public interface productMapper extends BaseMapper<product> {
    @Insert("insert into product values (#{pid},#{pname},#{price},#{image})")
    boolean insertProduct(product product);

    @Update("update product set pname = #{pname}, price = #{price}, image = #{image} where pid = #{pid}")
    boolean updateByPid(product product);

    @Delete("delete from product where pid = #{id}")
    boolean deletebyId(int id);
}
