package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_colleges")
public class Colleges {
    @TableId
    private Integer college_id;
    private String college_name;
    private String college_addr;
    private String college_tel;
    @TableLogic
    private int is_delete;
}
