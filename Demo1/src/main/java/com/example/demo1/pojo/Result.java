package com.example.demo1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;   //1成功，0失败

    private String msg;     //提示信息

    private Object data;    //数据data

    /*快速构建result*/
    public  static Result success(Object data){             /*查询*/
        return new Result(1,"success",data);
    }
    public  static Result success(){                        /*增删改*/
        return new Result(1,"success",null);
    }
    public  static Result error(String msg){                /*失败*/
        return new Result(0,msg,null);
    }




}
