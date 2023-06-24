package com.edu.adminsystem.controller.student;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.adminsystem.pojo.*;
import com.edu.adminsystem.service.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StuSubjectService stuSubjectService;
    @Autowired
    private SubjectClazzService subjectClazzService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StepsService stepsService;
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session,@RequestParam(value = "stepId",defaultValue = "-1") Integer stepId){
        Student student= (Student) session.getAttribute("user");
        Clazz clazz = clazzService.getById(student.getClazzId());
        List<SubjectClazz> subjectClazzList = subjectClazzService.query().eq("class_id", clazz.getId()).list();
        List<Integer> subIdList1=new ArrayList<>();
        if(subjectClazzList!=null) subIdList1=subjectClazzList.stream().map(SubjectClazz::getSubId).collect(Collectors.toList());
        List<StuSubject> stuSubjectList = stuSubjectService.query().eq("stu_id", student.getId()).list();
        List<Integer> subIdList2=new ArrayList<>();
        if(stuSubjectList!=null) subIdList2=stuSubjectList.stream().map(StuSubject::getCourseId).collect(Collectors.toList());
        List<Subject> subList1=subIdList1.stream().map(id->subjectService.getById(id)).collect(Collectors.toList());
        subList1.forEach(subject -> {
            subject.setTeacher(teacherService.getById(subject.getTeacherId()));
        });
        List<Subject> subList2=subIdList2.stream().map(id->subjectService.getById(id)).collect(Collectors.toList());
        subList2.forEach(subject -> {
            subject.setTeacher(teacherService.getById(subject.getTeacherId()));
            subject.setChoose(false);
        });
        subList1.addAll(subList2);
        List<Steps> steps = stepsService.list();
        session.setAttribute("steps",steps);
        List<Subject> chooseList=new ArrayList<>();
        subList1.forEach(s->{
            if(s.getStepId().equals(stepId)){
                chooseList.add(s);
            }
        });
        model.addAttribute("choose",subList1);
        session.setAttribute("subList",subList1);
        if(stepId!=-1) {
            model.addAttribute("choose", chooseList);
            session.setAttribute("subList",chooseList);
        }
        subIdList1.addAll(subIdList2);
        List<Subject> isChoosed;
        if(!subIdList1.isEmpty()&&stepId!=-1) {
             isChoosed= subjectService.query().notIn("course_id", subIdList1).eq("step_id",stepId).list();
        }else if(stepId==-1&&!subIdList1.isEmpty()){
            isChoosed= subjectService.query().notIn("course_id", subIdList1).list();
        }else if(stepId != 1) isChoosed= subjectService.query().eq("step_id",stepId).list();
         else isChoosed=subjectService.list();
         if(isChoosed==null) isChoosed=new ArrayList<>();
        isChoosed.forEach(subject -> {
            subject.setTeacher(teacherService.getById(subject.getTeacherId()));
        });
        model.addAttribute("isChoosed",isChoosed);
        model.addAttribute("stepId",stepId);
        return "student";
    }

    @GetMapping("/choose")
    public String choose(Integer id,@RequestParam(value = "stepId",defaultValue = "-1") Integer stepId,HttpSession session){
        StuSubject stuSubject=new StuSubject();
        Student user = (Student) session.getAttribute("user");
        stuSubject.setStuId(user.getId());
        stuSubject.setCourseId(id);
        stuSubjectService.save(stuSubject);
        subjectService.update().setSql("course_choosed=course_choosed+1").eq("course_id",id).update();
        return "redirect:/student/index?stepId="+stepId;
    }

    @GetMapping("/back")
    public String back(Integer id,@RequestParam(value = "stepId",defaultValue = "-1") Integer stepId,HttpSession session){
        Student user = (Student) session.getAttribute("user");
        stuSubjectService.remove(new QueryWrapper<StuSubject>().eq("stu_id",user.getId()).eq("course_id",id));
        subjectService.update().setSql("course_choosed=course_choosed-1").eq("course_id",id).update();
        return "redirect:/student/index?stepId="+stepId;
    }

    @GetMapping("/score")
    public String score(HttpSession session,Model model){
        List<Subject> subList = (List<Subject>) session.getAttribute("subList");
        Student student= (Student) session.getAttribute("user");
        subList.forEach(subject -> {
            subject.setCj(scoreService.query().eq("stu_num",student.getNum()).eq("course_id",subject.getId()).one());
        });
        model.addAttribute("subList",subList);
        return "studentScore";
    }
}
