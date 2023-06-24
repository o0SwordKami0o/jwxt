package com.edu.adminsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.adminsystem.mapper.CourseAndClassMapper;
import com.edu.adminsystem.pojo.Classes;
import com.edu.adminsystem.pojo.Colleges;
import com.edu.adminsystem.pojo.CourseAndClass;
import com.edu.adminsystem.pojo.Dto.CourseDto;
import com.edu.adminsystem.pojo.Profession;
import com.edu.adminsystem.service.ClassesService;
import com.edu.adminsystem.service.CollegeService;
import com.edu.adminsystem.service.CourseAndClassService;
import com.edu.adminsystem.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class CourseAndClassServiceImpl extends ServiceImpl<CourseAndClassMapper,CourseAndClass> implements CourseAndClassService {

    @Autowired
    private ClassesService classesService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private ProfessionService professionService;

    @Override
    public void removeByCouresId(int id) {
        LambdaQueryWrapper<CourseAndClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseAndClass::getCourse_id,id);
        baseMapper.delete(queryWrapper);
    }


    @Override
    public void saveByIds(Integer cid,List<Integer> ids) {
        LambdaQueryWrapper<CourseAndClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseAndClass::getCourse_id,cid);
        baseMapper.delete(queryWrapper);
        List<CourseAndClass> collect = ids.stream().map(id -> {
            CourseAndClass courseAndClass = new CourseAndClass();
            courseAndClass.setCourse_id(cid);
            courseAndClass.setClass_id(id);
            return courseAndClass;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

    @Override
    public List<CourseDto> getCourseAndColleges() {
        //所有课程
        List<Classes> classes = classesService.getBaseMapper().selectList(null);
        //所有专业
        List<Profession> professions = professionService.getBaseMapper().selectList(null);
        //所有学院
        List<Colleges> colleges = collegeService.getBaseMapper().selectList(null);
        List<CourseDto> res = new ArrayList<>();
        for (Colleges college : colleges) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCollegeName(college.getCollege_name());
            for (Profession profession : professions) {
                if(profession.getCid().equals(college.getCollege_id())){
                    for (Classes aClass : classes) {
                        if(aClass.getPro_id().equals(profession.getPro_id())){
                            courseDto.getList().add(aClass);
                        }
                    }
                }
            }
            res.add(courseDto);
        }
        return res;
    }

    @Override
    public List<Integer> getIdsByCid(Integer id) {
        LambdaQueryWrapper<CourseAndClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseAndClass::getCourse_id,id);
        List<CourseAndClass> courseAndClasses = baseMapper.selectList(queryWrapper);
        return courseAndClasses.stream().map(CourseAndClass::getClass_id).collect(Collectors.toList());
    }
}
