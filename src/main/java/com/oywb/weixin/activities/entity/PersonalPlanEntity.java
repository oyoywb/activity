package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "personal_plan", schema = "oywb_test", catalog = "")
public class PersonalPlanEntity {
    private long id;
    private String name;
    private String mode;
    private Timestamp start;
    private Timestamp end;
    private String remarks;
    private byte isAllDay;
    private byte isMind;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "mode", nullable = false, length = 255)
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Basic
    @Column(name = "start", nullable = true)
    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end", nullable = true)
    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    @Basic
    @Column(name = "remarks", nullable = true, length = 255)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "is_all_day", nullable = false)
    public byte getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(byte isAllDay) {
        this.isAllDay = isAllDay;
    }

    @Basic
    @Column(name = "is_mind", nullable = false)
    public byte getIsMind() {
        return isMind;
    }

    public void setIsMind(byte isMind) {
        this.isMind = isMind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalPlanEntity that = (PersonalPlanEntity) o;
        return id == that.id &&
                isAllDay == that.isAllDay &&
                isMind == that.isMind &&
                Objects.equals(name, that.name) &&
                Objects.equals(mode, that.mode) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mode, start, end, remarks, isAllDay, isMind);
    }
}
