package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_classes")
public class Clazz {
    @TableId(value = "class_id",type = IdType.AUTO)
    @TableField("class_id")
    Integer id;
    @TableField("class_name")
    String name;

}
/**
 * create table tb_classes(
 *     class_id int primary key auto_increment,
 *     class_name varchar(40) not null unique ,
 *     class_desc varchar(200),
 *     pro_id int not null ,
 *     constraint FK_CLASS_PROFESSION foreign key (pro_id) references tb_professions(pro_id)
 * );
 */