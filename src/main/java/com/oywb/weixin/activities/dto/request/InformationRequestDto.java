package com.oywb.weixin.activities.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class InformationRequestDto {
    private long id;

    private String title;

    private String welcomeMessage;

    private long activityId;

    private boolean needName;

    private boolean needSex;

    private boolean needAge;

    private boolean needSchool;

    //專業
    private boolean needSps;

    private boolean needGrade;

    private boolean needPhone;

    private boolean needEmail;

    private boolean needWechat;

    private List<String> customerQuestion;

    private String conclusion;

}
