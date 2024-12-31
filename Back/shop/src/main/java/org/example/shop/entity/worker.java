package org.example.shop.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

public class worker {
    @TableId
    private String wid;
    public Integer sid;
    public String wname;
    public String wphone;
    public String wsex;
    public String wpassword;
}
