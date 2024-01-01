package com.example.demo1.service.impl;

import com.example.demo1.mapper.UserMapper;
import com.example.demo1.pojo.PageBean;
import com.example.demo1.pojo.User;
import com.example.demo1.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServicecImpl implements UserService {

    @Autowired
    private UserMapper userMapper;  /*要调用Mapper层就要创建mapper对象*/

    /*查询全部数据*/
    @Override
    public List<User> list(){       /*重写返回值为集合的方法，调用Mapper方法中的lsit()方法*/
        return userMapper.list();
    }

    @Override
    public void deleteById(Integer id){
        userMapper.deleteById(id);
    }

    @Override
    public void insert(User user){
        user.setCreatDate(LocalDateTime.now()); /*用set方法设置属性为当前时间*/
        user.setUpdateDate(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    public List<User> selectById(Integer id){
        return userMapper.selectById(id);
    }

    @Override
    public void updateUser(User user){
        user.setUpdateDate(LocalDateTime.now());
        userMapper.updateUser(user);
    }

/*    @Override
    public PageBean page(Integer page,Integer pageSize){
        Long sum = userMapper.count();  //获取总数

        Integer start = ( page - 1 )* pageSize; //计算展示本页的起始编码
        List<User> list = userMapper.page(start,pageSize);  //调用分页查询

        PageBean pageBean = new PageBean(sum,list);   //用全参构造传递参数

        return pageBean;
    }*/

    /*分页查询：使用pagehelper插件调用其中的方法实现*/
    @Override
    public PageBean page(Integer page,Integer pageSize,String name,Integer gender,
                         LocalDateTime creatDate,LocalDateTime updateDate){
        PageHelper.startPage(page,pageSize);

        List<User> list = userMapper.list2(name,gender,creatDate, updateDate);   /*调用list2方法，此时的list2是个简单的查询，但已经被插件复杂化*/
        Page<User> p = (Page<User>) list;   /*List类型强转成page类型*/

        //封装至PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());   //用pagehelper中提供的两个方法获取总数和分页列表
        return pageBean;
    }

}
