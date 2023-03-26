package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class PlanResponseDto {

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

    private Timestamp ts;
}
