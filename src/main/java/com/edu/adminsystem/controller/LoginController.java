package com.edu.adminsystem.controller;

import com.edu.adminsystem.pojo.Admin;
import com.edu.adminsystem.pojo.Student;
import com.edu.adminsystem.pojo.Teacher;
import com.edu.adminsystem.service.AdminService;
import com.edu.adminsystem.service.StudentService;
import com.edu.adminsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @RequestMapping("/login.html")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/")
    public String path(){
        return "login";
    }

    @GetMapping("/login")
    public String login(String username, String password, int role,String code, HttpServletRequest request){
        if(!"8".equals(code)){
            request.getSession().setAttribute("msg","验证码错误");
            return "login";
        }
        switch (role){
            case 0:
                Student s=studentService.query().eq("stu_num",username).one();
                request.getSession().setAttribute("user",s);
                return "redirect:/student/index";
            case 1:
                Teacher t = teacherService.query().eq("teacher_num", username).one();
                request.getSession().setAttribute("user",t);
                return "redirect:/teacher/index";
            case 2:
                Admin admin = adminService.getByUsername(username);
                if(admin.getPassword().equals(password)){
                    request.getSession().setAttribute("user",admin);
                    return "admin";
                }
                break;
        }
        request.getSession().setAttribute("msg","账号或密码错误");
        return "login";
    }
}
