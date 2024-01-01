package com.example.demo1.service;

import com.example.demo1.pojo.PageBean;
import com.example.demo1.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    /*查询全部数据*/
    public List<User> list();

    public void deleteById(Integer id);

    public void insert(User user);

    public List<User> selectById(Integer id);

    public void updateUser(User user);

    public PageBean page(Integer page, Integer pageSize,String name, Integer gender,
                         LocalDateTime creatDate,LocalDateTime updateDate);
}
