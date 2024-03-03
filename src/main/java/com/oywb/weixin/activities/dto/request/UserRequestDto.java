package com.oywb.weixin.activities.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserRequestDto {
    private String name;

    private int sex;

    private Timestamp birthday;

    private String sign;
}
