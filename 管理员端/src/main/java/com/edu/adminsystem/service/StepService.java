package com.edu.adminsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.adminsystem.pojo.Step;

import java.util.Collection;
import java.util.List;

public interface StepService extends IService<Step> {
    List<Step> getExId(Integer step_id);
}
