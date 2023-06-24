package com.edu.adminsystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.adminsystem.pojo.Admin;

public interface AdminService extends IService<Admin> {
    Admin getByUsername(String username);

    Page<Admin> keywordPage(String keyword, Integer pageNo);
}
