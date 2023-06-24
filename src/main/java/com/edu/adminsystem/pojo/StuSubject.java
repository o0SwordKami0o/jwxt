package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_stu_course")
public class StuSubject {
    @TableField("stu_id")
    Integer stuId;
    @TableField("course_id")
    Integer courseId;
}
/**
 *
 *create table tb_stu_course(
         *stu_id int,
         *course_id int,
         *primary key(stu_id,course_id)
         *);
 */