package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_teachers")
public class Teacher {
    @TableId(value = "teacher_id",type = IdType.AUTO)
    @TableField("teacher_id")
    Integer id;
    @TableField("teacher_num")
    String num;
    @TableField("teacher_tel")
    String number;
    @TableField("teacher_name")
    String name;
    @TableField("teacher_gender")
    String sex;
    @TableField("teacher_age")
    Integer age;
}

/*
create table tb_teachers(
    teacher_id int primary key auto_increment,
    teacher_num char(5) not null unique ,
teacher_pwd varchar(20) not null ,
        teacher_name varchar(20) not null ,
        teacher_gender char(2) not null ,
        teacher_age int not null ,
        teacher_tel char(11) not null unique,
        teacher_resume varchar(400),
        pro_id int not null ,
        constraint FK_TEACHER_PROFESSION foreign key (pro_id) references tb_professions(pro_id)
        );
 */