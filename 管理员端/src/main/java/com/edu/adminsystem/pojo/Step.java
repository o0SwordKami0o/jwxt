package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_steps")
public class Step {
    @TableId
    private Integer step_id;
    private String step_name;
    @TableLogic
    private int is_delete;
}
