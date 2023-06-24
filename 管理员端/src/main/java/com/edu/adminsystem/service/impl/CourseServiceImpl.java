package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.CourseMapper;
import com.edu.adminsystem.pojo.Admin;
import com.edu.adminsystem.pojo.Course;
import com.edu.adminsystem.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Override
    public Page<Course> keywordPage(String keyword, Integer pageNo) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(keyword),Course::getCourse_name,keyword);
        Page<Course> page = new Page<>(pageNo,6);
        return baseMapper.selectPage(page,queryWrapper);
    }
}
