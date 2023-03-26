package com.oywb.weixin.activities.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private int id;
    private int userId;
    //頭像
    private byte[] profile;

    private String name;

    private String sex;

    private String birthday;

    private String sign;
}
