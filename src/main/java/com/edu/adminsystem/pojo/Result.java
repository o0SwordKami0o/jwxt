package com.edu.adminsystem.pojo;

import lombok.Data;

@Data
public class Result<T> {
    String msg;
    int code;
    T data;
}
