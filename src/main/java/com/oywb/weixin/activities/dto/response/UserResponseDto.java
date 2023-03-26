package com.oywb.weixin.activities.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    //頭像
    private byte[] profile;

    private String name;

    private String sex;

    private String birthday;

    private String sign;
}
