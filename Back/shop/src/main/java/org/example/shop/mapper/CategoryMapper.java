package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.shop.entity.Category;
import org.springframework.stereotype.Repository;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
