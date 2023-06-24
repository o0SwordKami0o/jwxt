package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_students")
public class Student {
    @TableId
    private Integer stu_id;
    private String stu_num;
    private String stu_pwd;
    private String stu_name;
    private String stu_gender;
    private Integer stu_age;
    private Integer stu_begin_year;
    private String stu_addr;
    private String stu_tel;
    private String stu_remark;
    private Integer class_id;
    @TableLogic
    private int is_delete;
}
