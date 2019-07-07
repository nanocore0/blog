package com.pianxian.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {

    String saveFile(MultipartFile file) throws IOException;

    File getFile(String filePath) throws IOException;
}
