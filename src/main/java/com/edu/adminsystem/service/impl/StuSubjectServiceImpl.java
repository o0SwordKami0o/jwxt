package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.StuSubjectMapper;
import com.edu.adminsystem.pojo.StuSubject;
import com.edu.adminsystem.service.StuSubjectService;
import org.springframework.stereotype.Service;

@Service
public class StuSubjectServiceImpl extends ServiceImpl<StuSubjectMapper, StuSubject> implements StuSubjectService {
}
