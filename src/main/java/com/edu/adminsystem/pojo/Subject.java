package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_courses")
public class Subject {
    @TableField("course_id")
    @TableId(value = "course_id",type = IdType.AUTO)
    Integer id;
    @TableField("teacher_id")
    Integer teacherId;
    @TableField("course_name")
    String name;
    @TableField("course_xf")
    Double score;
    @TableField("course_time")
    Integer time;
    @TableField("course_cap")
    Integer volume;
    @TableField("course_choosed")
    Integer choosed;
    @TableField("step_id")
    Integer StepId;
    @TableField(exist = false)
    Teacher teacher;
    @TableField(exist = false)
    boolean isChoose=true;
    @TableField(exist = false)
    Score cj;
}

/**
 *     course_id int primary key auto_increment,
 *     course_name varchar(50) not null ,
 *     course_desc varchar(400) not null ,
 *     course_xf int not null ,
 *     course_time int not null ,
 *     course_cap int not null ,
 *     course_choosed int not null ,
 *     choose_enable int not null ,
 *      teacher_id int,
 *      step_id int not null,
 */
