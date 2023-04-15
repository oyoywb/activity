package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ProjectResponseDto {

    private long id;

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

    private List<String> picture;

    //目前情況
    private String currentSituation;

    private boolean pass;

    //已報名人數
    private int passCount;

}
