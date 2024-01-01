package com.example.demo1.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String name;
    private LocalDateTime creatDate;
    private LocalDateTime updateDate;
    private Integer gender;
    private Integer phone;
}
