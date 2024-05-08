package org.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.shop.entity.Product;
import org.springframework.stereotype.Repository;

// 标记为 MyBatis 的 Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {
    // 这里可以添加一些特定的数据库操作方法，但基本的 CRUD 操作已经由 BaseMapper 提供
}
