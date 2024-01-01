package com.example.demo1.mapper;

import com.example.demo1.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from depart.user")    /*简单查询，注解方式写select语句*/
    List<User> list();                      /*返回类型为集合的list()方法*/

    @Delete("delete from depart.user where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into depart.user(name, gender,creat_date, update_date,  phone) values (#{name},#{gender},#{creatDate},#{updateDate},#{phone})")
    void insert(User user);

    @Select("select * from depart.user where id = #{id}")
    List<User> selectById(Integer id);

    void updateUser(User user);

/*    @Select("select count(*) from depart.user")
    public long count();

    @Select("select * from depart.user limit #{start},#{pageSize}")
    public List<User> page(Integer start, Integer pageSize);*/

    /*分页查询,多条件一定要加@Param！！！不然会导致找不到每一个参数*/
    public List<User> list2(@Param("name") String name, @Param("gender") Integer gender,
                            @Param("creatDate") LocalDateTime creatDate,@Param("updateDate")
                                    LocalDateTime updateDate);
}
