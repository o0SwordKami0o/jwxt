package com.edu.adminsystem.pojo.Dto;

import com.edu.adminsystem.pojo.Classes;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseDto {
    private String collegeName;
    private List<Classes> list = new ArrayList<>();
}
