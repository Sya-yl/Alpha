package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.shop.entity.Favorites;

@Mapper
public interface FavoritesMapper extends BaseMapper<Favorites> {
}
