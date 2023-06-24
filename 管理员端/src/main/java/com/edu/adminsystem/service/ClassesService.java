package com.edu.adminsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.adminsystem.pojo.Classes;

import java.util.List;

public interface ClassesService extends IService<Classes> {
    List<Classes> getExId(Integer id);

    List<Classes> getByIds(List<Integer> ids);
}
