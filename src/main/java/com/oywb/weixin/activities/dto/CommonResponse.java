package com.oywb.weixin.activities.dto;

import lombok.Data;

@Data
public class CommonResponse<T> {
    private int code;
    private String message;
    private T data;
}
