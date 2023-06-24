package com.edu.adminsystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.adminsystem.pojo.Student;
import com.edu.adminsystem.pojo.Teacher;

public interface StudentService extends IService<Student> {
    Page<Student> keywordPage(String keyword, Integer pageNo);
}
