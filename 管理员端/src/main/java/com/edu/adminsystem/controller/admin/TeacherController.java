package com.edu.adminsystem.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.adminsystem.pojo.Admin;
import com.edu.adminsystem.pojo.Dto.TeacherDto;
import com.edu.adminsystem.pojo.Profession;
import com.edu.adminsystem.pojo.Teacher;
import com.edu.adminsystem.service.ProfessionService;
import com.edu.adminsystem.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ProfessionService professionService;

    @RequestMapping("/index")
    public String index(){
        return "redirect:/teacher/page";
    }

    @GetMapping("/delete")
    public String delete(int id){
        teacherService.removeById(id);
        return "redirect:/teacher/page";
    }

    @GetMapping("/add")
    public String add(HttpServletRequest request){
        List<Profession> exId = professionService.getExId(null);
        request.setAttribute("Professions",exId);
        return "teacherAdd";
    }

    @PostMapping("/addTeacher")
    public String addAdmin(Teacher teacher){
        if(teacher.getTeacher_num().length()!=5){
            return "redirect:/teacher/add";
        }
        teacherService.save(teacher);
        return "redirect:/teacher/page";
    }

    @GetMapping("/update")
    public String update(Integer id, HttpServletRequest request){
        Teacher byId = teacherService.getById(id);
        request.setAttribute("teacher",byId);
        Profession profession = professionService.getById(byId.getPro_id());
        List<Profession> professions = new ArrayList<>();
        professions.add(profession);
        List<Profession> pros = professionService.getExId(byId.getPro_id());
        professions.addAll(pros);
        request.setAttribute("Professions",professions);
        List<Teacher> list = new ArrayList<>();
        list.add(byId);
        Teacher teacher = new Teacher();
        if(byId.getTeacher_gender().equals("男")){
            teacher.setTeacher_gender("女");
        }else {
            teacher.setTeacher_gender("男");
        }
        list.add(teacher);
        request.setAttribute("SexList",list);
        return "teacherUpdate";
    }

    @PostMapping("/updateTeacher")
    public String updateAdmin(Teacher teacher){
        if(teacher.getTeacher_num().length()!=5){
            return "redirect:/teacher/update?id="+teacher.getTeacher_id();
        }
        teacherService.updateById(teacher);
        return "redirect:/teacher/page";
    }

    @GetMapping("/page")
    public String page(String keyword,Integer pageNo, HttpServletRequest request){
        if(pageNo==null){
            pageNo = 1;
        }
        Page<Teacher> page = teacherService.keywordPage(keyword,pageNo);
        request.setAttribute("TeacherList",page.getRecords());
        request.setAttribute("pageCount",page.getPages());
        request.setAttribute("pageNo",pageNo);
        if(keyword!=null){
            request.setAttribute("keyword",keyword);
        }
        return "teacher";
    }
}
