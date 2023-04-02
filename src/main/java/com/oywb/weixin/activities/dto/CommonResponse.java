package com.oywb.weixin.activities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse<T> {
    private int code;
    private String message;
    private T data;
}
