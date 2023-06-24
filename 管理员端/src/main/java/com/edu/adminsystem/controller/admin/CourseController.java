package com.edu.adminsystem.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.adminsystem.pojo.*;
import com.edu.adminsystem.pojo.Dto.CourseDto;
import com.edu.adminsystem.service.*;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StepService stepService;
    @Autowired
    private CourseAndClassService courseAndClassService;


    @RequestMapping("/index")
    public String index(){
        return "redirect:/course/page";
    }

    @GetMapping("/delete")
    @Transactional
    public String delete(int id){
        courseService.removeById(id);
        courseAndClassService.removeByCouresId(id);
        return "redirect:/course/page";
    }

    @GetMapping("/add")
    public String add(HttpServletRequest request){
        List<Teacher> teachers = teacherService.getExId(null);
        List<Step> steps = stepService.getExId(null);
        request.setAttribute("teachers",teachers);
        request.setAttribute("steps",steps);
        List<CourseDto> courseDtoList = courseAndClassService.getCourseAndColleges();
        request.setAttribute("courseDtoList",courseDtoList);
        return "courseAdd";
    }

    @PostMapping("/addCourse")
    @Transactional
    public String addAdmin(Course course,@RequestParam("ids") List<Integer> ids){
        courseService.save(course);
        if(ids!=null && ids.size()>0){
            courseAndClassService.saveByIds(course.getCourse_id(),ids);
        }
        return "redirect:/course/page";
    }

    @GetMapping("/update")
    public String update(Integer id, HttpServletRequest request){
        Course byId = courseService.getById(id);
        request.setAttribute("course",byId);

        Teacher teacher = teacherService.getById(byId.getTeacher_id());
        Step step = stepService.getById(byId.getStep_id());
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        List<Step> steps = new ArrayList<>();
        steps.add(step);
        teachers.addAll(teacherService.getExId(byId.getTeacher_id()));
        steps.addAll(stepService.getExId(byId.getStep_id()));
        request.setAttribute("teachers",teachers);
        request.setAttribute("steps",steps);

        List<CourseDto> courseDtoList = courseAndClassService.getCourseAndColleges();
        request.setAttribute("courseDtoList",courseDtoList);

        List<Integer> checkedIds = courseAndClassService.getIdsByCid(id);
        request.setAttribute("checkedIds",checkedIds);
        return "courseUpdate";
    }

    @PostMapping("/updateCourse")
    @Transactional
    public String updateAdmin(Course course,@RequestParam("ids") List<Integer> ids){
        courseService.updateById(course);
        if(ids!=null && ids.size()>0){
            courseAndClassService.saveByIds(course.getCourse_id(),ids);
        }
        return "redirect:/course/page";
    }

    @GetMapping("/page")
    public String page(String keyword,Integer pageNo, HttpServletRequest request){
        if(pageNo==null){
            pageNo = 1;
        }
        Page<Course> page = courseService.keywordPage(keyword,pageNo);
        request.setAttribute("CourseList",page.getRecords());
        request.setAttribute("pageCount",page.getPages());
        request.setAttribute("pageNo",pageNo);
        if(keyword!=null){
            request.setAttribute("keyword",keyword);
        }
        return "course";
    }
}
