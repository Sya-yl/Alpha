package org.example.shop.controller;

import org.example.shop.entity.Favorites;
import org.example.shop.mapper.FavoritesMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FavoritesControllerTest {

    @Mock
    private FavoritesMapper favoritesMapper;

    @InjectMocks
    private FavoritesController favoritesController;

    private static final Integer TEST_FAVORITE_ID = 1;


    @Test
    public void testGetAllFavorites() {
        Favorites favorite1 = new Favorites();
        Favorites favorite2 = new Favorites();
        List<Favorites> mockFavoritesList = Arrays.asList(favorite1, favorite2);

        when(favoritesMapper.selectList(null)).thenReturn(mockFavoritesList);

        List<Favorites> result = favoritesController.getAllFavorites();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        verify(favoritesMapper, times(1)).selectList(null);
    }

    @Test
    public void testGetFavoriteById() {
        Favorites mockFavorite = new Favorites();
        mockFavorite.setId(TEST_FAVORITE_ID);
        mockFavorite.setName("Test Favorite");

        when(favoritesMapper.selectById(TEST_FAVORITE_ID)).thenReturn(mockFavorite);

        Favorites result = favoritesController.getFavoriteById(TEST_FAVORITE_ID);

        assertNotNull(result);
        assertEquals(TEST_FAVORITE_ID, result.getId());
        verify(favoritesMapper, times(1)).selectById(TEST_FAVORITE_ID);
    }

    @Test
    public void testAddFavorite() {
        Favorites favoriteToAdd = new Favorites();
        favoriteToAdd.setName("New Favorite");

        doNothing().when(favoritesMapper).insert(any(Favorites.class));

        favoritesController.addFavorite(favoriteToAdd);

        verify(favoritesMapper, times(1)).insert(favoriteToAdd);
    }

    @Test
    public void testDeleteFavoriteById() {
        doNothing().when(favoritesMapper).deleteById(TEST_FAVORITE_ID);

        favoritesController.deleteFavoriteById(TEST_FAVORITE_ID);

        verify(favoritesMapper, times(1)).deleteById(TEST_FAVORITE_ID);
    }
}