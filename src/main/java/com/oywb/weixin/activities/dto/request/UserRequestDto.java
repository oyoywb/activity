package com.oywb.weixin.activities.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private long userId;
    //頭像
    private byte[] profile;

    private String name;

    private int sex;

    private int birthday;

    private String sign;
}
