package com.edu.adminsystem.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.adminsystem.pojo.Admin;
import com.edu.adminsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/index")
    public String index(){
        return "redirect:/admin/page";
    }

    @GetMapping("/delete")
    public String delete(int id){
        adminService.removeById(id);
        return "redirect:/admin/page";
    }

    @GetMapping("/add")
    public String add(){
        return "adminAdd";
    }

    @PostMapping("/addAdmin")
    public String addAdmin(Admin admin,HttpServletRequest request){
        if(admin.getMgr_num().length()!=5){
            return "redirect:/admin/add";
        }
        Admin byUsername = adminService.getByUsername(admin.getMgr_num());
        if(byUsername!=null){
            return "redirect:/admin/add";
        }
        adminService.save(admin);
        return "redirect:/admin/page";
    }

    @GetMapping("/update")
    public String update(Integer id, HttpServletRequest request){
        Admin byId = adminService.getById(id);
        request.setAttribute("admin",byId);
        List<Admin> list = new ArrayList<>();
        list.add(byId);
        Admin admin = new Admin();
        if(byId.getMgr_gender().equals("男")){
            admin.setMgr_gender("女");
        }else {
            admin.setMgr_gender("男");
        }
        list.add(admin);
        request.setAttribute("SexList",list);
        return "adminUpdate";
    }

    @PostMapping("/updateAdmin")
    public String updateAdmin(Admin admin,HttpServletRequest request){
        if(admin.getMgr_num().length()!=5){
            return "redirect:/admin/update?id="+admin.getMgr_id();
        }
        Admin byUsername = adminService.getByUsername(admin.getMgr_num());
        if(byUsername!=null){
            return "redirect:/admin/update?id="+admin.getMgr_id();
        }
        adminService.updateById(admin);
        System.out.println(admin);
        return "redirect:/admin/page";
    }

    @GetMapping("/page")
    public String page(String keyword,Integer pageNo, HttpServletRequest request){
        if(pageNo==null){
            pageNo = 1;
        }
        Page<Admin> page = adminService.keywordPage(keyword,pageNo);
        request.setAttribute("AdminList",page.getRecords());
        request.setAttribute("pageCount",page.getPages());
        request.setAttribute("pageNo",pageNo);
        if(keyword!=null){
            request.setAttribute("keyword",keyword);
        }
        return "admin";
    }
}
