package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.StepMapper;
import com.edu.adminsystem.pojo.Steps;
import com.edu.adminsystem.service.StepsService;
import org.springframework.stereotype.Service;

@Service
public class StepsServiceImpl extends ServiceImpl<StepMapper, Steps> implements StepsService {
}
