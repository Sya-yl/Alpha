package org.example.shop.controller;

import org.example.shop.ShopApplication;
import org.example.shop.entity.User;
import org.example.shop.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ShopApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserController userController;
    @Autowired
    private MockMvc mockMvc;
    private User user;

    @BeforeEach
    public void setUp() {
        // 初始化一个用户对象
        user = new User();
        user.setId(1);
        user.setName("TestUser");
        user.password = "password123"; // 注意这里使用了public字段，但在实际项目中建议封装成getter/setter
        user.setEmail("test@example.com");
        user.setPhonenumber("1234567890");
        user.setAddress("Test Address");
        user.setPermissions(1); // 普通用户
    }

    // 登录成功测试
    @Test
    public void testLoginSuccess() {
        // 模拟userMapper.selectById返回正确的用户对象
        when(userMapper.selectById(1)).thenReturn(user);
        // 调用login方法并传入正确的密码
        boolean result = userController.login(1, "password123");
        // 断言结果为true
        assertTrue(result, "Login should be successful with correct password");
        // 验证userMapper.selectById方法被调用了一次
        verify(userMapper, times(1)).selectById(1);
    }
    // 登录失败测试
    @Test
    public void testLoginFailure() {
        // 模拟userMapper.selectById返回正确的用户对象
        when(userMapper.selectById(1)).thenReturn(user);
        // 调用login方法并传入错误的密码
        boolean result = userController.login(1, "wrongPassword");
        // 断言结果为false
        assertFalse(result, "Login should fail with incorrect password");
        // 验证userMapper.selectById方法被调用了一次
        verify(userMapper, times(1)).selectById(1);
    }

    // 查找用户测试
    @Test
    public void testFindUser() {
        // 模拟userMapper.selectById返回正确的用户对象
        when(userMapper.selectById(1)).thenReturn(user);
        // 调用find方法
        User foundUser = userController.find(1);
        // 断言返回的用户对象与模拟的对象相同
        assertEquals(user, foundUser, "Returned user should be the same as the one in the database");
        // 验证userMapper.selectById方法被调用了一次
        verify(userMapper, times(1)).selectById(1);
    }

    @Test
    void testDelete() {
        // 模拟删除成功
        when(userMapper.deleteById(anyInt())).thenReturn(1);
        // 测试删除功能
        assertEquals("Success", userController.delete(1));
        // 模拟删除失败
        when(userMapper.deleteById(anyInt())).thenReturn(0);
        // 测试删除失败的情况
        assertEquals("Fail", userController.delete(2));
    }

    @Test
    void testModifySuccess() {
        // 模拟更新成功
        when(userMapper.updateById(any())).thenReturn(1);
        User user = new User(); // 创建一个用户对象，用于测试
        // 测试修改功能
        assertTrue(userController.modify(user));
    }

    @Test
    void testModifyFailure() {
        // 模拟更新失败
        when(userMapper.updateById(any())).thenReturn(0);
        User user = new User(); // 创建一个于测试的用户对象
        // 测试修改失败的情况
        assertFalse(userController.modify(user));
    }
}