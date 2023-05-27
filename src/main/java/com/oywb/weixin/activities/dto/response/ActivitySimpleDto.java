package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ActivitySimpleDto {
    private long id;
    private String location;
    private String title;

    private String content;

    private String reaper;

    private int count;

    private List<UserSimpleInfo> userSimpleInfos;

    @Data
    public class UserSimpleInfo {
        private Long id;
        private String profile;
    }
}
