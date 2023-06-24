package com.edu.adminsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.adminsystem.pojo.Admin;

public interface AdminService extends IService<Admin> {
    Admin getByUsername(String username);
}
