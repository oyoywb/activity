package com.oywb.weixin.activities.entity;

import com.oywb.weixin.activities.dto.response.ActivityResponseDto;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "activity")
@DynamicInsert
@DynamicUpdate
public class ActivityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "start")
    private Timestamp start;
    @Basic
    @Column(name = "end")
    private Timestamp end;
    @Basic
    @Column(name = "count")
    private Integer count;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "organizer")
    private String organizer;
    @Basic
    @Column(name = "introduction")
    private String introduction;
    @Basic
    @Column(name = "rule")
    private String rule;
    @Basic
    @Column(name = "form")
    private String form;
    @Basic
    @Column(name = "collect_or_not")
    private Byte collectOrNot;
    @Basic
    @Column(name = "recommand")
    private String recommand;
    @Basic
    @Column(name = "reaper")
    private String reaper;
    @Basic
    @Column(name = "verified")
    private Byte verified;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "school")
    private String school;
    @Basic
    @Column(name = "campus")
    private String campus;

    @Basic
    @Column(name = "picture")
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Byte getCollectOrNot() {
        return collectOrNot;
    }

    public void setCollectOrNot(Byte collectOrNot) {
        this.collectOrNot = collectOrNot;
    }

    public String getRecommand() {
        return recommand;
    }

    public void setRecommand(String recommand) {
        this.recommand = recommand;
    }

    public String getReaper() {
        return reaper;
    }

    public void setReaper(String reaper) {
        this.reaper = reaper;
    }

    public Byte getVerified() {
        return verified;
    }

    public void setVerified(Byte verified) {
        this.verified = verified;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(type, that.type) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(count, that.count) && Objects.equals(location, that.location) && Objects.equals(organizer, that.organizer) && Objects.equals(introduction, that.introduction) && Objects.equals(rule, that.rule) && Objects.equals(form, that.form) && Objects.equals(collectOrNot, that.collectOrNot) && Objects.equals(recommand, that.recommand) && Objects.equals(reaper, that.reaper) && Objects.equals(verified, that.verified) && Objects.equals(userId, that.userId) && Objects.equals(school, that.school) && Objects.equals(campus, that.campus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, start, end, count, location, organizer, introduction, rule, form, collectOrNot, recommand, reaper, verified, userId, school, campus);
    }

    public ActivityResponseDto toActivityResponseDto() {
        ActivityResponseDto activityResponseDto = new ActivityResponseDto();
        activityResponseDto.setId(this.id);
        activityResponseDto.setTitle(this.title);
        activityResponseDto.setPicture(Arrays.asList(this.picture.split(",")));
        activityResponseDto.setType(this.type);
        activityResponseDto.setStart(this.start);
        activityResponseDto.setEnd(this.end);
        activityResponseDto.setCount(this.count);
        activityResponseDto.setLocation(this.location);
        activityResponseDto.setOrganizer(this.organizer);
        activityResponseDto.setIntroduction(this.introduction);
        activityResponseDto.setRule(this.rule);
        activityResponseDto.setForm(this.form);
        activityResponseDto.setCollectOrNot(this.collectOrNot ==  1);
        activityResponseDto.setRecommand(this.recommand);
        activityResponseDto.setVerified(this.verified == 1);
        activityResponseDto.setUserId(this.userId);
        activityResponseDto.setSchool(this.school);
        activityResponseDto.setCampus(this.campus);

        return activityResponseDto;
    }
}
