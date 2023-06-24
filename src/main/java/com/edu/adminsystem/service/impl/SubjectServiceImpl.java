package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.SubjectMapper;
import com.edu.adminsystem.service.SubjectService;
import com.edu.adminsystem.pojo.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper,Subject> implements SubjectService {
    @Override
    public List<Subject> getclazz(Integer TeaId) {
        List<Subject> list = this.query().eq("teacherId", TeaId).list();
        return list;
    }
}
