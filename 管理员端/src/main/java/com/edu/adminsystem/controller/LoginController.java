package com.edu.adminsystem.controller;

import com.edu.adminsystem.pojo.Admin;
import com.edu.adminsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/")
    public String loginPage(){
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
                break;
            case 1:
                break;
            case 2:
                Admin admin = adminService.getByUsername(username);
                if(admin!=null && admin.getMgr_pwd().equals(password)){
                    request.getSession().setAttribute("user",admin);
                    return "aindex";
                }
                break;
        }
        request.setAttribute("msg","账号或密码错误");
        return "login";
    }

    @GetMapping("/back")
    public String back(){
        return "aindex";
    }
}
