package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_teachers")
public class Teacher {
    @TableId
    private Integer teacher_id;
    private String teacher_num;
    private String teacher_pwd;
    private String teacher_name;
    private String teacher_gender;
    private Integer teacher_age;
    private String teacher_tel;
    private String teacher_resume;
    private Integer pro_id;
    @TableLogic
    private int is_delete;
}
