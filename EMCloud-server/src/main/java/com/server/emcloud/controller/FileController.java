package com.server.emcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 王俊博
 * 处理文件上传
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/load")
    public String upload(@RequestParam("files")MultipartFile aa, HttpServletRequest request) throws IOException {

         aa.transferTo(new File("C:\\EMCloud\\datafiles\\imgs",aa.getOriginalFilename()));//文件上传
         return "200"; //
    }
}
