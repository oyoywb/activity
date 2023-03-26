package com.oywb.weixin.activities.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class InformationDetailRequestDto {
    private int id;

    private String name;

    private int age;

    private int sex;

    private String school;

    private String sps;

    private String grade;

    private String phone;

    private String email;

    private String wechat;

    private int activityId;

    private boolean passed;

    private int userId;

    private Map<String, String> custom_question;
}
