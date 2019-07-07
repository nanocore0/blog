package com.pianxian.blog.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

/**
 * @description: 传入图片的base64
 * @author: KimJun
 * @date: 18/11/13 00:25
 */
public class base64ToMultipart {
    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            //BASE64Decoder decoder = new BASE64Decoder();
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] b = new byte[0];
            //b = decoder.decodeBuffer(baseStrs[1]);
            b = decoder.decode(baseStrs[1]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //测试
    public static void main(String[] args) {
        MultipartFile multipartFiles[] = new MultipartFile[2];
        multipartFiles[0] = base64ToMultipart.base64ToMultipart("");
        System.out.println(multipartFiles[0].getSize());

    }
}
