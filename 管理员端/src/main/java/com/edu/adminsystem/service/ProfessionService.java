package com.edu.adminsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.adminsystem.pojo.Profession;

import java.util.List;

public interface ProfessionService extends IService<Profession> {
    List<Profession> getExId(Integer pro_id);
}
