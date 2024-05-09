package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    public String password;
    private String email;
    private String phonenumber;
    private String address;
    private int permissions; //权限级别：0表示管理员，1表示普通用户

    public void setId(int id) {
        this.id = id;
    }
}
