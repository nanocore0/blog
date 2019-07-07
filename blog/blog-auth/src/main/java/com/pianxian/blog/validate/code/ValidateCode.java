package com.pianxian.blog.validate.code;

import java.time.LocalDateTime;

public class ValidateCode {

    /**
     * 验证码
     */
    protected String code;
    /**
     * 过期时间
     */
    protected LocalDateTime expireTime;

    /**
     *
     * @param code 验证码
     * @param expireIn 有效期
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }


}
