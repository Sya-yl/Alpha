package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.StringTypeHandler;
import org.example.shop.entity.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    // 在ProductMapper接口中定义插入方法
    @Insert({
            "INSERT INTO product (productid, productname, description, price, categoryid, image)VALUES (#{productid}, #{productname}, #{description}, #{price}, #{categoryid},#{image, jdbcType=VARCHAR, typeHandler=org.apache.ibatis.type.StringTypeHandler})"
    })
    int insertProduct(Product product);
}
