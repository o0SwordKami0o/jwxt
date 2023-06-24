package com.edu.adminsystem.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@ControllerAdvice
@Controller
public class GlobalExceptionHandler {
    /**
     * 学号和邮箱重复添加异常处理
     * 需要数据库中学号和邮箱字段为唯一字段
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String exceptionHandler(SQLIntegrityConstraintViolationException ex, Model model){
        if(ex.getMessage().contains("Duplicate entry")){
            //学号或邮箱已存在"
            model.addAttribute("msg","账号已存在");
            return "redirect:/admin/page";
        }
        //未知错误
        return "login";
    }
}
