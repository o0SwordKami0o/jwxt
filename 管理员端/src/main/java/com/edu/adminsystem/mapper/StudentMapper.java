package com.edu.adminsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.adminsystem.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
