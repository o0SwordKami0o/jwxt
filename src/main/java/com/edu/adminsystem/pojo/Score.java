package com.edu.adminsystem.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_grades")
public class Score {
    @TableField("stu_num")
    String stuId;
    @TableField("course_id")
    Integer subId;
    @TableField("score")
    Double score;
}

/**
 * create table tb_grades(
 *     stu_num char(11) not null ,
 *     course_id int not null ,
 *     score int,
 *     primary key (stu_num,course_id),
 *     constraint FK_GRADE_STUDENT foreign key (stu_num) references tb_students(stu_num),
 *     constraint FK_GRADE_COURSE foreign key (course_id) references tb_courses(course_id)
 * );
 */
