package com.pianxian.blog.controller;

import com.pianxian.blog.common.BASE64DecodedMultipartFile;
import com.pianxian.blog.common.Response;
import com.pianxian.blog.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FileService fileService;

    @PostMapping("/upload/file")
    public String test(@RequestParam(value = "file", required = false) String pictureFile1, HttpServletRequest request){
        MultipartFile multipartFile = BASE64DecodedMultipartFile.base64ToMultipart(pictureFile1);
        try {
            String fileRelativePath = fileService.saveFile(multipartFile);
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            String filePath = serverPath + fileRelativePath;
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // file, img, base64Date
    @PostMapping({"/base64", "/upload/img", "/upload/base64"})
    public String upload(@RequestParam(value = "file", required = false) MultipartFile pictureFile1,
                                      @RequestParam(value = "img", required = false) MultipartFile pictureFile2,
                                      @RequestParam(value = "base64Date", required = false) MultipartFile pictureFile3,
                                      HttpServletRequest request) throws FileNotFoundException {
        MultipartFile pictureFile;
        if (pictureFile1 != null) {
            pictureFile = pictureFile1;
        } else if (pictureFile2 != null) {
            pictureFile = pictureFile2;
        } else if (pictureFile3 != null) {
            pictureFile = pictureFile3;
        } else {
            throw new IllegalArgumentException("上传图片为空");
        }

        try {
            String fileRelativePath = fileService.saveFile(pictureFile);
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            String filePath = serverPath + fileRelativePath;

            /*Map<String, String> map = new HashMap<>();
            map.put("fileName", pictureFile.getOriginalFilename());
            map.put("filePath", filePath);
            map.put("fileRelativePath", fileRelativePath);
            return map;*/
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //@GetMapping("/file/{filePath}")
    public void download(@PathVariable String filePath, HttpServletResponse response) throws IOException {
        File file = fileService.getFile(filePath);

        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        try {
            int b = 0;
            byte[] buffer = new byte[512];
            while (b != -1) {
                b = in.read(buffer);
                out.write(buffer, 0, b);
            }
            in.close();
            out.close();
            out.flush();
        } catch (Exception e) {
        }
    }
}
