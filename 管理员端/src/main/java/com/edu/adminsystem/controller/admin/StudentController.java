package com.edu.adminsystem.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.adminsystem.pojo.*;
import com.edu.adminsystem.service.ClassesService;
import com.edu.adminsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassesService classesService;

    @RequestMapping("/index")
    public String index(){
        return "redirect:/student/page";
    }

    @GetMapping("/delete")
    public String delete(int id){
        studentService.removeById(id);
        return "redirect:/student/page";
    }

    @GetMapping("/add")
    public String add(HttpServletRequest request){
        List<Classes> exId = classesService.getExId(null);
        request.setAttribute("Classes",exId);
        return "studentAdd";
    }

    @PostMapping("/addStudent")
    public String addAdmin(Student student){
        if(student.getStu_num().length()!=11){
            return "redirect:/student/add";
        }
        studentService.save(student);
        return "redirect:/student/page";
    }

    @GetMapping("/update")
    public String update(Integer id, HttpServletRequest request){
        Student byId = studentService.getById(id);
        request.setAttribute("student",byId);
        Classes classes = classesService.getById(byId.getClass_id());
        List<Classes> clazz = new ArrayList<>();
        clazz.add(classes);
        List<Classes> pros = classesService.getExId(byId.getClass_id());
        clazz.addAll(pros);
        request.setAttribute("Clazz",clazz);
        List<Student> list = new ArrayList<>();
        list.add(byId);
        Student student = new Student();
        if(byId.getStu_gender().equals("男")){
            student.setStu_gender("女");
        }else {
            student.setStu_gender("男");
        }
        list.add(student);
        request.setAttribute("SexList",list);
        return "studentUpdate";
    }

    @PostMapping("/updateStudent")
    public String updateAdmin(Student student){
        if(student.getStu_num().length()!=11){
            return "redirect:/student/update?id="+student.getStu_id();
        }
        studentService.updateById(student);
        return "redirect:/student/page";
    }

    @GetMapping("/page")
    public String page(String keyword,Integer pageNo, HttpServletRequest request){
        if(pageNo==null){
            pageNo = 1;
        }
        Page<Student> page = studentService.keywordPage(keyword,pageNo);
        request.setAttribute("StudentList",page.getRecords());
        request.setAttribute("pageCount",page.getPages());
        request.setAttribute("pageNo",pageNo);
        if(keyword!=null){
            request.setAttribute("keyword",keyword);
        }
        return "student";
    }
}
