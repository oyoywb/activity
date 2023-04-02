package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "information", schema = "oywb_test", catalog = "")
public class InformationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "welcome_message")
    private String welcomeMessage;
    @Basic
    @Column(name = "activity_id")
    private long activityId;
    @Basic
    @Column(name = "need_name")
    private byte needName;
    @Basic
    @Column(name = "need_sex")
    private byte needSex;
    @Basic
    @Column(name = "need_age")
    private byte needAge;
    @Basic
    @Column(name = "need_school")
    private byte needSchool;
    @Basic
    @Column(name = "need_sps")
    private byte needSps;
    @Basic
    @Column(name = "need_grade")
    private byte needGrade;
    @Basic
    @Column(name = "need_phone")
    private byte needPhone;
    @Basic
    @Column(name = "need_email")
    private byte needEmail;
    @Basic
    @Column(name = "need_wechat")
    private byte needWechat;
    @Basic
    @Column(name = "custom_question")
    private String customQuestion;
    @Basic
    @Column(name = "conclusion")
    private String conclusion;

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

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public byte getNeedName() {
        return needName;
    }

    public void setNeedName(byte needName) {
        this.needName = needName;
    }

    public byte getNeedSex() {
        return needSex;
    }

    public void setNeedSex(byte needSex) {
        this.needSex = needSex;
    }

    public byte getNeedAge() {
        return needAge;
    }

    public void setNeedAge(byte needAge) {
        this.needAge = needAge;
    }

    public byte getNeedSchool() {
        return needSchool;
    }

    public void setNeedSchool(byte needSchool) {
        this.needSchool = needSchool;
    }

    public byte getNeedSps() {
        return needSps;
    }

    public void setNeedSps(byte needSps) {
        this.needSps = needSps;
    }

    public byte getNeedGrade() {
        return needGrade;
    }

    public void setNeedGrade(byte needGrade) {
        this.needGrade = needGrade;
    }

    public byte getNeedPhone() {
        return needPhone;
    }

    public void setNeedPhone(byte needPhone) {
        this.needPhone = needPhone;
    }

    public byte getNeedEmail() {
        return needEmail;
    }

    public void setNeedEmail(byte needEmail) {
        this.needEmail = needEmail;
    }

    public byte getNeedWechat() {
        return needWechat;
    }

    public void setNeedWechat(byte needWechat) {
        this.needWechat = needWechat;
    }

    public String getCustomQuestion() {
        return customQuestion;
    }

    public void setCustomQuestion(String customQuestion) {
        this.customQuestion = customQuestion;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationEntity that = (InformationEntity) o;
        return id == that.id && activityId == that.activityId && needName == that.needName && needSex == that.needSex && needAge == that.needAge && needSchool == that.needSchool && needSps == that.needSps && needGrade == that.needGrade && needPhone == that.needPhone && needEmail == that.needEmail && needWechat == that.needWechat && Objects.equals(title, that.title) && Objects.equals(welcomeMessage, that.welcomeMessage) && Objects.equals(customQuestion, that.customQuestion) && Objects.equals(conclusion, that.conclusion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, welcomeMessage, activityId, needName, needSex, needAge, needSchool, needSps, needGrade, needPhone, needEmail, needWechat, customQuestion, conclusion);
    }
}
