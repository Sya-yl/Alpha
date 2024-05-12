package org.example.shop.controller;

import org.example.shop.entity.Favorites;
import org.example.shop.mapper.FavoritesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
public class FavoritesController {
    private final FavoritesMapper favoritesMapper;

    @Autowired
    public FavoritesController(FavoritesMapper favoritesMapper) {
        this.favoritesMapper = favoritesMapper;
    }

    // 获取全部收藏
    @GetMapping("/favorites")
    public List<Favorites> getAllFavorites() {
        return favoritesMapper.selectList(null);
    }

    // 根据收藏ID获取单个收藏
    @GetMapping("/favorites/{id}")
    public Favorites getFavoriteById(@PathVariable("id") Integer id) {
        return favoritesMapper.selectById(id);
    }

    // 添加新的收藏
    @PostMapping("/favorites")
    public boolean addFavorite(@RequestBody Favorites favorite) {
        int rows= favoritesMapper.insert(favorite);
        return rows>0;
    }

    // 删除收藏
    @DeleteMapping("/favorites/{id}")
    public boolean deleteFavoriteById(@PathVariable("id") Integer id) {
        int rows = favoritesMapper.deleteById(id);
        return rows>0;
    }
}
