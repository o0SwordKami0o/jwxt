package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.ProfessionMapper;
import com.edu.adminsystem.pojo.Profession;
import com.edu.adminsystem.service.ProfessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionServiceImpl extends ServiceImpl<ProfessionMapper, Profession> implements ProfessionService {
    @Override
    public List<Profession> getExId(Integer pro_id) {
        LambdaQueryWrapper<Profession> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(pro_id!=null,Profession::getPro_id,pro_id);
        return baseMapper.selectList(queryWrapper);
    }
}
