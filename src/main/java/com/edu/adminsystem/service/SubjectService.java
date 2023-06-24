package com.edu.adminsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.adminsystem.pojo.Subject;

import java.util.List;

public interface SubjectService extends IService<Subject> {
    List<Subject> getclazz(Integer TeaId);
}
