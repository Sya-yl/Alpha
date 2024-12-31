package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.shop.entity.orders;
import org.example.shop.entity.worker;

import java.util.List;

@Mapper
public interface workerMapper extends BaseMapper<worker> {
    @Insert("insert into worker values (#{wid},#{sid},#{wname},#{wsex},#{wphone},#{wpassword})")
    int insert(worker worker);

    @Select("select * from orders where wid = #{wid}")
    List<orders> getByWid(int wid);
}
