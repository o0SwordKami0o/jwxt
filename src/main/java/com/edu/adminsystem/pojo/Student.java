package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_students")
public class Student {
    @TableId(value = "stu_id",type = IdType.AUTO)
    @TableField("stu_id")
    int id;
    @TableField("stu_name")
    String name;
    @TableField("stu_num")
    String num;
    @TableField(exist = false)
    Double score;
    @TableField(exist = false)
    String clazzName;
    @TableField("class_id")
    Integer clazzId;
}
/**
 * create table tb_students(
 *     stu_id int primary key auto_increment,
 *     stu_num char(11) not null unique ,
 *stu_pwd varchar(20)not null,
         *stu_name varchar(20)not null,
         *stu_gender char(2)not null,
         *stu_age int not null,
         *stu_begin_year int not null,
         *stu_addr varchar(200)not null,
         *stu_tel char(11)not null unique,
         *stu_remark varchar(200),
         *class_id int not null,
         *constraint FK_STUDENT_CLASS foreign key(class_id)references tb_classes(class_id)
         *);
 */
