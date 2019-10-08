package com.pianxian.blog.file.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@ConfigurationProperties(prefix = "blog.file.upload")
public class FileUploadProperties {

    String path = ResourceUtils.getURL("classpath:static/images/").getPath();
    String datePattern = "yyyy/MM/dd";

    public FileUploadProperties() throws FileNotFoundException {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}
