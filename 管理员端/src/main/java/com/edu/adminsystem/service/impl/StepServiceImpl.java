package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.StepMapper;
import com.edu.adminsystem.pojo.Step;
import com.edu.adminsystem.pojo.Teacher;
import com.edu.adminsystem.service.StepService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepServiceImpl extends ServiceImpl<StepMapper, Step> implements StepService {
    @Override
    public List<Step> getExId(Integer step_id) {
        LambdaQueryWrapper<Step> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(step_id!=null,Step::getStep_id,step_id);
        return baseMapper.selectList(queryWrapper);
    }
}
