package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.CollegeMapper;
import com.edu.adminsystem.pojo.Colleges;
import com.edu.adminsystem.service.CollegeService;
import org.springframework.stereotype.Service;

@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, Colleges> implements CollegeService {
}
