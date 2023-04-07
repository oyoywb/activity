package com.oywb.weixin.activities.entity;

import com.oywb.weixin.activities.dto.request.PersonalInfoDto;
import com.oywb.weixin.activities.dto.response.PlanResponseDto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "personal_plan", schema = "oywb_test", catalog = "")
public class PersonalPlanEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "mode")
    private String mode;
    @Basic
    @Column(name = "start")
    private Timestamp start;
    @Basic
    @Column(name = "end")
    private Timestamp end;
    @Basic
    @Column(name = "remarks")
    private String remarks;
    @Basic
    @Column(name = "is_all_day")
    private byte isAllDay;
    @Basic
    @Column(name = "is_mind")
    private byte isMind;
    @Basic
    @Column(name = "cron")
    private String cron;
    @Basic
    @Column(name = "is_top")
    private byte isTop;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public byte getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(byte isAllDay) {
        this.isAllDay = isAllDay;
    }

    public byte getIsMind() {
        return isMind;
    }

    public void setIsMind(byte isMind) {
        this.isMind = isMind;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public byte getIsTop() {
        return isTop;
    }

    public void setIsTop(byte isTop) {
        this.isTop = isTop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalPlanEntity that = (PersonalPlanEntity) o;
        return id == that.id && userId == that.userId && isAllDay == that.isAllDay && isMind == that.isMind && isTop == that.isTop && Objects.equals(name, that.name) && Objects.equals(mode, that.mode) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(remarks, that.remarks) && Objects.equals(cron, that.cron);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, mode, start, end, remarks, isAllDay, isMind, cron, isTop);
    }

    public PlanResponseDto toPlanResponseDto() {
        PlanResponseDto planResponseDto = new PlanResponseDto();
        planResponseDto.setId(this.id);
        planResponseDto.setUserId(this.userId);
        planResponseDto.setName(this.name);
        planResponseDto.setMode(this.mode);
        planResponseDto.setAllDay(this.isAllDay == 1 ? true : false);
        planResponseDto.setMind(this.isMind == 1 ? true : false);
        planResponseDto.setRemarks(this.remarks);
        planResponseDto.setTop(this.isTop == 1 ? true : false);

        return planResponseDto;

    }
}
