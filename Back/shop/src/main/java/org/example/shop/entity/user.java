package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

public class user {
    @TableId
    public String uid;
    public String uname;
    public String upassword;
    public String uphone;
    public String uaddress;
    public String usex;
    public Integer permisson;
}
