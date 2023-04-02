package com.oywb.weixin.activities.dto.response;

import lombok.Data;

//簡歷
@Data
public class ResumeResponseDto {
    private long id;

    private long userId;

    private byte[] avatar;

    private String name;

    private String grade;

    //學院
    private String college;

    //專業
    private String og;

    private String skill;

    //經歷
    private String experience;

    //興趣
    private String interest;

    private String introduction;

    private boolean online;

    private String school;
}
