package com.example.demo1.controller;

import com.example.demo1.pojo.PageBean;
import com.example.demo1.pojo.Result;
import com.example.demo1.pojo.User;
import com.example.demo1.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j  /*日志记录注解*/
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;    /*创建对应的service对象，以便后面的方法调用*/

    /*指定请求方式、路径*/
    @GetMapping
    public Result list(){       /*查询方法：调用service层*/
        List<User> userList = userService.list();   /*调用service对象中的list()方法，返回给泛型为User的集合userList*/
        log.info("查询");
        return Result.success(userList);    /*返回值遵循Result中的success(返回的集合)方法*/
    }
    /*根据id删除*/
    @DeleteMapping("/{id}")      /*进地址栏需要添加Path注解*/
    public Result deleteById(@PathVariable Integer id){
        userService.deleteById(id);
        log.info("根据id删除:",id);
        return Result.success();
    }

    /*新增一个*/
    @PostMapping
    public Result insert(@RequestBody User user){   /*json类型需要添加注解*/
        userService.insert(user);
        log.info("添加成功",user);
        return Result.success();
    }

    /*根据id查询数据*/
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        List<User> list = userService.selectById(id);
        return Result.success(list);
    }
    /*修改信息*/
    @PutMapping
    public Result updateUser(@RequestBody User user){   /*！！！注意json类型参数的注解！！！！！*/
        userService.updateUser(user);
        return Result.success();
    }

    /*分页条件查询*/
    @GetMapping("/limit")
    public Result page(@RequestParam(defaultValue = "1") Integer page,  //设置初始值
                       @RequestParam(defaultValue = "2") Integer pageSize,
                       String name,Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") LocalDateTime creatDate,
                       @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") LocalDateTime updateDate){
        log.info("分页查询：",page,name,creatDate,updateDate,page,pageSize);
        PageBean pageBean = userService.page(page,pageSize,name,gender,creatDate,updateDate);
        return Result.success(pageBean);
    }

}
