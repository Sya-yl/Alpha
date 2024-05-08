package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.shop.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
//
//    @Select("select * from user")
//    public User find();
//
//    @Insert("insert into user values (#{id},#{name},#{password},#{email},#{phonenumber},#{address},#{permissions})")
//    public int insert(User user); //user表新增数据
}
