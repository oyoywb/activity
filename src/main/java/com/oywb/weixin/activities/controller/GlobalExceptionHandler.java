package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse handleException(Exception e) {
        log.error("服务器异常", e);
        return CommonResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("服务器异常,调用接口失败，请联系管理员处理")
                .build();
    }
}
