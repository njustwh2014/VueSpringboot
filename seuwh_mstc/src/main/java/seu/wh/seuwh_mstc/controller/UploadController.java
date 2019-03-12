/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Value("F:/git_repository/VueSpringboot/images")
//    @Value("/home/huanhuan/myweb/images")
    private String baseFolderPath;

    @RequestMapping("/upload")
    @ResponseBody
    @RequiresAuthentication

    //接收图片的参数名需要为"editormd-image-file"
    public JSONObject saveImage(@RequestParam("editormd-image-file") MultipartFile image, HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        StringBuffer url = new StringBuffer();
//        String filePath = sdf.format(new Date());
        File baseFolder = new File(baseFolderPath);
        if (!baseFolder.exists()) {
            baseFolder.mkdirs();
        }
//        url.append(request.getScheme())
//                .append("://")
//                .append(request.getServerName())
//                .append(":")
//                .append(request.getServerPort())
//                .append(request.getContextPath());
        url.append("http://localhost:8000");
//        url.append("http://47.100.58.57:3000");
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {

            File dest = new File(baseFolder, imgName);
            image.transferTo(dest);

            url.append("/").append(imgName);
            jsonObject.put("url", url);//图片回显地址，即文件存放地址，应为虚拟路径
            jsonObject.put("success", 1);//图片上传成功的信息码
            jsonObject.put("message", "upload success!");//信息



        } catch (IOException e) {
            logger.error("upload file failed",e);
            jsonObject.put("success", 0);//图片上传成功的信息码
            jsonObject.put("message", "upload failed!");//信息
        }
        return jsonObject;
    }
}
