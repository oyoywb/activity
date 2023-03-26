package com.oywb.weixin.activities.dto.request;

import lombok.Data;

@Data
public class PlanRequestDto {
    private int id;
    private int userId;

    private String name;

    private String mode;

    private boolean isAllDay;

    private boolean isMind;

    //備註
    private String remarks;

    private String cron;

    private boolean isTop;


}
