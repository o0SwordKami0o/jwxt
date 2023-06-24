package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.StudentMapper;
import com.edu.adminsystem.pojo.Student;
import com.edu.adminsystem.pojo.Teacher;
import com.edu.adminsystem.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public Page<Student> keywordPage(String keyword, Integer pageNo) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(keyword),Student::getStu_name,keyword);
        Page<Student> page = new Page<>(pageNo,6);
        return baseMapper.selectPage(page,queryWrapper);
    }
}
