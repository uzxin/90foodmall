package com.cuit.foodmall.common;

import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author: YX
 * @date: 2020/2/20 10:42
 * @description: 图片上传
 */
@RestController
@Slf4j
public class FileController {

    @PostMapping("fileUpload")
    public Object fileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        //判断文件是否为空
        if (file.isEmpty()) {
            return Result.error("文件为空");
        }
        //文件名
        String fileName = file.getOriginalFilename();
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        //后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //格式限制
        String[] s = new String[]{".bmp", ".jpg", ".jpeg", ".png", ".gif"};
        if (!Arrays.asList(s).contains(suffixName)){
            System.out.println(!Arrays.asList(s).contains(suffixName));
            return Result.error("格式不正确");
        }
        try {
            //项目class目录绝对路径
            String path = ResourceUtils.getFile("classpath:").getParent();

            fileName = UUID.randomUUID() + suffixName;
            String url = "/upload/" + fileName;
            //保存路径
            File upload = new File(path + url);
            if (!upload.getParentFile().exists()) {
                upload.getParentFile().mkdirs();
            }
            //数据库保存的url
            log.info("Upload url: " + upload.getAbsolutePath());

            //保存文件
            file.transferTo(upload);
            //保存返回的数据
            Map<String, String> data = new HashMap<>();
            data.put("url", url);
            return Result.ok("保存成功", data);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("保存出错");
        }
    }

}
