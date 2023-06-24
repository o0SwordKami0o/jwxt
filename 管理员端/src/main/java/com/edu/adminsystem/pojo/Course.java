package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_courses")
public class Course {
    @TableId
    private Integer course_id;
    private String course_name;
    private String course_desc;
    private Integer course_xf;
    private Integer course_time;
    private Integer course_cap;
    private Integer course_choosed;
    private Integer choose_enable;
    private Integer teacher_id;
    private Integer step_id;
    @TableLogic
    private int is_delete;
}
