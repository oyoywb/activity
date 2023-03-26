package com.oywb.weixin.activities.dto.request;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ActivityRequestDto {
    private int id;

    private List<byte[]> picture;

    private String title;

    private String type;

    private Timestamp start;

    private Timestamp end;

    private int count;

    private String location;

    private String organizer;

    private String introduction;

    private String rule;

    //形式
    private String form;

    private boolean collectOrNot;

    private String recommand;

    private boolean verified;

    private int userId;

    private String school;

    private String campus;

    private InformationRequestDto informationRequestDto;
}
