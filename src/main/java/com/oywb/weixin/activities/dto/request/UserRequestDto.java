package com.oywb.weixin.activities.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserRequestDto {
    private String name;

    private int sex;

    private String birthday;

    private String sign;
}
