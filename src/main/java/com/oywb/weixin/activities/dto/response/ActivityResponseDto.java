package com.oywb.weixin.activities.dto.response;

import com.oywb.weixin.activities.dto.request.InformationRequestDto;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ActivityResponseDto {
    private long id;

    private List<String> picture;

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

    private long userId;

    private String school;

    private String campus;

    private InformationRequestDto informationRequestDto;

    //已報名人數
    private long passCount;
}
