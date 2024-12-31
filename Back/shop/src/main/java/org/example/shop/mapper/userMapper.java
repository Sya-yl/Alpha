package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.shop.entity.shoppingcart;
import org.example.shop.entity.user;

import java.util.List;

@Mapper
public interface userMapper extends BaseMapper<user> {
    @Select("select * from user")
    List<user> find();

    @Insert("insert into user(uid,uname,uaddress,uphone,upassword,usex) values (#{uid},#{uname},#{uaddress},#{uphone},#{upassword},#{usex})")
    boolean user_insert(user user); //user表新增数据

    @Insert("insert into shoppingcart(cartid,uid) values (#{cartid},#{uid})")
    boolean shoppppingcart_insert(shoppingcart cart);
}
