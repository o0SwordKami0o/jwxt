package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class Admin {
    private int id;
    private String username;
    private String password;
    @TableLogic
    private int is_deleted;
}
