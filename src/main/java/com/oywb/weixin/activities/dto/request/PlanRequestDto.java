package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.PersonalPlanEntity;
import lombok.Data;

@Data
public class PlanRequestDto {
    private long id;

    private long userId;

    private String name;

    private String mode;

    private boolean isAllDay;

    private boolean isMind;

    //備註
    private String remarks;

    private String cron;

    private boolean isTop;

    public PersonalPlanEntity toPersonalPlanEntity() {
        PersonalPlanEntity personalPlanEntity = new PersonalPlanEntity();

        personalPlanEntity.setUserId(this.userId);
        personalPlanEntity.setName(this.name);
        personalPlanEntity.setMode(this.mode);
        personalPlanEntity.setIsAllDay((byte) (this.isAllDay ? 1 : 0));
        personalPlanEntity.setIsMind((byte) (this.isMind ? 1 : 0));
        personalPlanEntity.setRemarks(this.remarks);
        personalPlanEntity.setCron(this.cron);
        personalPlanEntity.setIsTop((byte) (this.isTop ? 1 : 0));

        return personalPlanEntity;

    }


}
