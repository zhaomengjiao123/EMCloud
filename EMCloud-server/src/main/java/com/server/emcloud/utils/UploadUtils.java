package com.server.emcloud.utils;

import java.io.File;
//import org.apache.tomcat.jni.File;

public class UploadUtils {
    //静态资源路径
    public final static String IMG_PATH_PREFIX = "C:\\EMCloud\\EMCloud-server\\src\\main\\resources\\image";

    public static File getImgDirFile() {
        //dome是项目名
        String fileDirPath = new String( IMG_PATH_PREFIX);

        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {

            fileDir.mkdirs();
        }
        return fileDir;
    }
}