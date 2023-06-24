package com.edu.adminsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_professions")
public class Profession {
    @TableId
    private Integer pro_id;
    private String pro_name;
    private String pro_desc;
    private Integer cid;
    @TableLogic
    private int is_delete;
}
