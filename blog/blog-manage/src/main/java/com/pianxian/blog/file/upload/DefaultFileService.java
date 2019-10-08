package com.pianxian.blog.file.upload;

import com.pianxian.blog.file.autoconfigure.FileUploadProperties;
import com.pianxian.blog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class DefaultFileService implements FileService {

    @Autowired
    FileUploadProperties fileUploadProperties;

    @Override
    public String saveFile(MultipartFile file) throws IOException {
            String originalFilename = file.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1); //不要 '.'

            String realPath = fileUploadProperties.getPath();
            String datePath = DateTimeFormatter.ofPattern(fileUploadProperties.getDatePattern()).format(LocalDateTime.now());
            String newFilename = UUID.randomUUID().toString();
            File newFile = new File(realPath +  datePath + "/" + newFilename + "." + ext);

            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            file.transferTo(newFile);

            String filePath = "/images/" + datePath + "/" + newFilename + "." + ext;
            return filePath;
    }

    @Override
    public File getFile(String filePath) throws FileNotFoundException {
        // images/2019/06/19/9f314fc2-a04a-41d3-aa79-9f271ba97e7b.jpg
        String realPath = fileUploadProperties.getPath();
        String abstractPath = realPath + "/" + filePath;
        System.out.println(abstractPath);
        File file = ResourceUtils.getFile(abstractPath);
        return file;
    }

}
