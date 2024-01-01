package com.example.demo1.controller;

import com.example.demo1.pojo.Result;
import com.example.demo1.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

   /* *//*本地存储*//*
    @PostMapping("/upload")
    public Result upload(String username, String age, MultipartFile image) throws IOException { *//*transferTo抛出异常*//*
        log.info("文件上传：",username,age,image);

        *//*构造唯一的文件名-uuid（通用唯一识别码）uuid.random*//*
        String filename = image.getOriginalFilename();  *//*获取原始名：调用image中API的getfilename方法获取文件名*//*
        int index = filename.lastIndexOf(".");  *//*捕捉最后一个.的位置*//*
        String extname = filename.substring(index); *//*获得文件扩展名：截取获取捕捉的位置之后及的字符串*//*
        String newFileName = UUID.randomUUID().toString()+extname;  *//*生成唯一识别码+文件扩展名构成新的文件名*//*

        image.transferTo(new File("D:\\Practice\\resource\\"+newFileName));    //指定文件的本地保存路径，注意路径后面有\\不然没法拼接文件名！！

        return Result.success();
    }*/

    /*阿里云存储*/
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件：",image);
        String url = aliOSSUtils.upload(image);  /*调用工具类实现文件上传*/

        return Result.success(url); /*将文件的url返回给前端*/
    }

}
