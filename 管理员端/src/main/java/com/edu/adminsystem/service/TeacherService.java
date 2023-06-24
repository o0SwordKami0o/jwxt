package com.edu.adminsystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.adminsystem.pojo.Teacher;

import java.util.Collection;
import java.util.List;

public interface TeacherService extends IService<Teacher> {
    Page<Teacher> keywordPage(String keyword, Integer pageNo);

    List<Teacher> getExId(Integer teacher_id);
}
