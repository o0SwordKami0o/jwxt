package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.AdminMapper;
import com.edu.adminsystem.pojo.Admin;
import com.edu.adminsystem.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public Admin getByUsername(String username) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getMgr_num,username);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<Admin> keywordPage(String keyword, Integer pageNo) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(keyword),Admin::getMgr_name,keyword);
        Page<Admin> page = new Page<>(pageNo,6);
        return baseMapper.selectPage(page,queryWrapper);
    }
}
