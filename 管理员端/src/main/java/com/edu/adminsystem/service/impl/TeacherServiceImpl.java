package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.TeacherMapper;
import com.edu.adminsystem.pojo.Teacher;
import com.edu.adminsystem.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public Page<Teacher> keywordPage(String keyword, Integer pageNo) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(keyword),Teacher::getTeacher_name,keyword);
        Page<Teacher> page = new Page<>(pageNo,6);
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Teacher> getExId(Integer teacher_id) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(teacher_id!=null,Teacher::getTeacher_id,teacher_id);
        return baseMapper.selectList(queryWrapper);
    }
}
