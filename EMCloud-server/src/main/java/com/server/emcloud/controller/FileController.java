package com.server.emcloud.controller;

import com.server.emcloud.utils.ObjectRESTResult;
import com.server.emcloud.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * lyx
 * 图片处理
 */
@RestController
@RequestMapping("/file")
public class FileController {



    @ResponseBody
    @RequestMapping("/upload/img")
    public String upload1(@RequestParam("file") MultipartFile imgFile, HttpServletRequest request, HttpSession session){
        System.out.println("图片测试");

        ObjectRESTResult restResult = new ObjectRESTResult();

        // 生成唯一文件名
        String uuid = UUID.randomUUID().toString().trim();
        String filename = imgFile.getOriginalFilename();
        int index=filename.indexOf(".");
        String fileNames= uuid + filename;

        // 调用UploadUtils工具类将图片存放到服务器上
        File fileDir = UploadUtils.getImgDirFile();

        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + fileNames);
            System.out.println(newFile.getAbsolutePath());
            imgFile.transferTo(newFile);


        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> emp = new HashMap<>();
        emp.put("fileName",fileNames);
        //返回图片名称
        restResult.setReturnObj(emp);
        fileNames="http://localhost:8080/img/"+fileNames;
        return fileNames;

    }



}