package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_course_class")
public class SubjectClazz {
    @TableField("course_id")
    Integer subId;
    @TableField("class_id")
    Integer clazId;
}
/**
 * create table tb_course_class(
 *     course_id int not null ,
 *     class_id int not null ,
 *     primary key (course_id,class_id),
 *     constraint FK_COURSE_CLASS1 foreign key (course_id) references tb_courses(course_id),
 *     constraint FK_COURSE_CLASS2 foreign key (class_id) references tb_classes(class_id)
 * );
 */