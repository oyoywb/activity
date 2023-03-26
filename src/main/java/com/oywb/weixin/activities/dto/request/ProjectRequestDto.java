package com.oywb.weixin.activities.dto.request;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ProjectRequestDto {

    private int id;

    //截止日期
    private Timestamp end;

    private String name;

    private String tag;

    private String organizer;

    private String location;

    private String introduction;

    //項目進度
    private String rop;

    private String period;

    private int count;

    //規模
    private int scope;

    private String expect;

    private List<byte[]> picture;

    //目前情況
    private String currentSituation;

    private String pass;
}
