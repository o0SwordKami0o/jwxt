package com.edu.adminsystem.controller.teacher;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.adminsystem.pojo.*;
import com.edu.adminsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resources;
import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/teacher")
public class teacherController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectClazzService subjectClazzService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private StepsService stepsService;
    @Autowired
    private StuSubjectService stuSubjectService;

    @GetMapping("/index")
    public String index(HttpSession session, Model model,
                        @RequestParam(value = "stepId",defaultValue = "-1") Integer stepId){
        Teacher user = (Teacher) session.getAttribute("user");
        List<Steps> steps = stepsService.list();
        List<Subject> list;
        if(stepId!=-1){
            list=subjectService.query().eq("teacher_id", user.getId()).eq("step_id",stepId).list();
        }else list=subjectService.query().eq("teacher_id", user.getId()).list();
        model.addAttribute("clazzList",list);
        session.setAttribute("clazzList",list);
        session.setAttribute("steps",steps);
        return "teacher";
    }

    @GetMapping("/score")
    public String selectScore(Integer id, Model model,
                              @RequestParam(value = "pn",defaultValue = "1") Integer pn){
        Subject sub = subjectService.query().eq("course_id",id).one();
        model.addAttribute("sub",sub);
        List<Integer> clazzList = subjectClazzService.query().eq("course_id", id).list().stream().map(SubjectClazz::getClazId).collect(Collectors.toList());
        Page<Student> page=new Page<>(pn,6);
        List<StuSubject> stuSubList = stuSubjectService.query().eq("course_id", id).list();
        List<Integer> stuIdList=null;
        if(stuSubList!=null&&!stuSubList.isEmpty()){
            stuIdList=stuSubList.stream().map(StuSubject::getStuId).collect(Collectors.toList());
        }
        Page<Student> students=page;
        if(stuIdList != null&&!clazzList.isEmpty()&&!stuIdList.isEmpty()){
            students=studentService.query().in("stu_id",stuIdList).or().in("class_id",clazzList).page(page);
        }
        else if(!clazzList.isEmpty()) students = studentService.query().in("class_id",clazzList).page(page);
        else if(stuIdList != null&&!stuIdList.isEmpty()) students=studentService.query().in("stu_id",stuIdList).page(page);
        students.getRecords().forEach(student -> {
            Score one = scoreService.query().eq("stu_num", student.getNum()).eq("course_id", id).one();
            if(one!=null) student.setScore(one.getScore());
            student.setClazzName(clazzService.getById(student.getClazzId()).getName());
        });
        model.addAttribute("students",students);
        return "score";
    }

    @GetMapping("/score/show")
    public String showScore(Integer cid,Integer sid,Model model){
        Student student = studentService.getById(sid);
        Score score = scoreService.query().eq("course_id", cid).eq("stu_num",student.getNum()).one();
        Subject sub = subjectService.query().eq("course_id",cid).one();
        model.addAttribute("score",score);
        model.addAttribute("sub",sub);
        model.addAttribute("stu",student);
        return "scoreUpdate";
    }

    @GetMapping("/score/update")
    public String updateScore(Integer cid,Integer sid,Double score){
        Student student = studentService.getById(sid);
        Score one = scoreService.query().eq("stu_num", student.getNum()).eq("course_id", cid).one();
        if(one!=null) {
            scoreService.update().eq("stu_num", student.getNum()).eq("course_id", cid).set("score", score).update();
        }else{
            Score sc=new Score();
            sc.setScore(score);
            sc.setSubId(cid);
            sc.setStuId(student.getNum());
            scoreService.save(sc);
        }
        return "redirect:/teacher/score?id="+cid;
    }
    @GetMapping("/score/add")
    public String addScore(Integer cid,Model model){
        Subject sub = subjectService.query().eq("id", cid).one();
        model.addAttribute("sub",sub);
        return "addScore";
    }
    @PostMapping("/score/add")
    public String addScoreByid(Integer id,String stuName,Integer number,Double score){
        Student student = studentService.query().eq("name", stuName).one();
        Score sc=new Score();
        sc.setScore(score);
        sc.setSubId(id);

        scoreService.save(sc);
        return "redirect:/teacher/score?id="+id;
    }

}
