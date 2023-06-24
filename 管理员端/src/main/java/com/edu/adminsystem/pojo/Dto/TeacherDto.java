package com.edu.adminsystem.pojo.Dto;

import lombok.Data;

@Data
public class TeacherDto {
    private Integer teacher_id;
    private String teacher_num;
    private String teacher_pwd;
    private String teacher_name;
    private String teacher_gender;
    private Integer teacher_age;
    private String teacher_tel;
    private String teacher_resume;
    private String pro_name;
}
