package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.ClassesMapper;
import com.edu.adminsystem.pojo.Classes;
import com.edu.adminsystem.service.ClassesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {
    @Override
    public List<Classes> getExId(Integer id) {
        LambdaQueryWrapper<Classes> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(id!=null,Classes::getClass_id,id);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Classes> getByIds(List<Integer> ids) {
        return baseMapper.selectBatchIds(ids);
    }
}
