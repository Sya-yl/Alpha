package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.shop.entity.OrderDetail;
import org.springframework.stereotype.Repository;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
