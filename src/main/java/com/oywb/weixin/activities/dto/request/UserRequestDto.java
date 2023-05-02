package com.oywb.weixin.activities.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;

    private int sex;

    private long birthday;

    private String sign;
}
