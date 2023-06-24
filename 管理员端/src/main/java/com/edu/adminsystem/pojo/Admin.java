package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_managers")
public class Admin {
    @TableId
    private Integer mgr_id;
    private String mgr_num;
    private String mgr_pwd;
    private String mgr_name;
    private String mgr_gender;
    private Integer mgr_age;
    private String mgr_tel;
    @TableLogic
    private int is_delete;
}
