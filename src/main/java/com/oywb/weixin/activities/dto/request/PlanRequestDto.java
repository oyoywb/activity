package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.PersonalPlanEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PlanRequestDto {
    private long id;

    private long userId;

    private String name;

    private String mode;

    private Boolean isAllDay;

    private Boolean isMind;

    //備註
    private String remarks;

    private String cron;

    private Boolean isTop;

    private Timestamp start;

    private Timestamp end;

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
        personalPlanEntity.setStart(this.start);
        personalPlanEntity.setEnd(this.end);

        return personalPlanEntity;

    }

    public void updatePersonalPlanEntity(PersonalPlanEntity personalPlanEntity) {
        if (this.name != null) {
            personalPlanEntity.setName(this.name);
        }
        if (this.mode != null) {
            personalPlanEntity.setMode(this.mode);
        }
        if (this.start != null) {
            personalPlanEntity.setStart(this.start);
        }
        if (this.end != null) {
            personalPlanEntity.setEnd(this.end);
        }
        if (this.remarks != null) {
            personalPlanEntity.setRemarks(this.remarks);
        }
        if (this.isAllDay != null) {
            personalPlanEntity.setIsAllDay((byte) (this.isAllDay ? 1 : 0));
        }
        if (this.isMind != null) {
            personalPlanEntity.setIsMind((byte) (this.isMind ? 1 : 0));
        }
        if (this.isTop != null) {
            personalPlanEntity.setIsTop((byte) (this.isTop ? 1 : 0));
        }
        if (this.cron != null) {
            personalPlanEntity.setCron(this.cron);
        }
    }

}
