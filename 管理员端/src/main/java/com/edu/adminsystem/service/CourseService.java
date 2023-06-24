package com.edu.adminsystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.adminsystem.pojo.Course;

public interface CourseService extends IService<Course> {
    Page<Course> keywordPage(String keyword, Integer pageNo);
}
