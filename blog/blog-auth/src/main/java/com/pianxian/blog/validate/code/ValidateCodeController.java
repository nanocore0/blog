package com.pianxian.blog.validate.code;

import com.pianxian.blog.validate.code.image.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
public class ValidateCodeController {

    private static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_FOR_CODE_IMAGE";

    @Autowired
    ValidateCodeGenerator validateCodeGenerator;

    @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成图形验证码
        ImageCode imageCode = (ImageCode) validateCodeGenerator.generate();
        // 将图形验证码放入sesssion中
        request.getSession().setAttribute(SESSION_KEY_IMAGE_CODE, imageCode);
        // 将图形写入到响应输出流中
        response.setContentType("image/jpeg");
        ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());
    }

}
