package com.pianxian.blog.validate.code.image;

import com.pianxian.blog.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图形验证码
 */
public class ImageCode extends ValidateCode {

    /**
     * 验证码图像
     */
    protected BufferedImage image;

    /**
     * @param image
     * @param code
     * @param expireIn 有效期，秒
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
