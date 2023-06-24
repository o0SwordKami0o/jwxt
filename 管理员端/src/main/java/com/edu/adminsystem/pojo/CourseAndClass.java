package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_course_class")
public class CourseAndClass {
    private Integer course_id;
    private Integer class_id;
}
