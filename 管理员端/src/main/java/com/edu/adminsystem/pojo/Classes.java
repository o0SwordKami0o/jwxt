package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_classes")
@Data
public class Classes {
    @TableId
    private Integer class_id;
    private String class_name;
    private String class_desc;
    private Integer pro_id;
    @TableLogic
    private int is_delete;
}
