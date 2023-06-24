package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_steps")
public class Steps {
    @TableId(value = "step_id",type = IdType.AUTO)
    @TableField("step_id")
    Integer id;
    @TableField("step_name")
    String name;
}
/**
 *
 *create table tb_steps(
         *step_id int primary key auto_increment,
         *step_name varchar(20)not null unique
         *);
 */
