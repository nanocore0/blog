package com.pianxian.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 */
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> handleIllegalArgumentException(Exception e) {
        e.printStackTrace();
        Map<String, Object> map = new HashMap<>();
        map.put("baka", "笨蛋，错误的参数");
        map.put("message", e.getMessage());
        return map;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> handleOtherException(Exception e) {
        e.printStackTrace();
        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }
}
