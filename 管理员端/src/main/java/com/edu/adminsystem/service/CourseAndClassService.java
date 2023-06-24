package com.edu.adminsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.adminsystem.pojo.Classes;
import com.edu.adminsystem.pojo.CourseAndClass;
import com.edu.adminsystem.pojo.Dto.CourseDto;

import java.util.List;

public interface CourseAndClassService extends IService<CourseAndClass> {
    void removeByCouresId(int id);

    void saveByIds(Integer cid,List<Integer> ids);

    List<CourseDto> getCourseAndColleges();

    List<Integer> getIdsByCid(Integer id);
}
