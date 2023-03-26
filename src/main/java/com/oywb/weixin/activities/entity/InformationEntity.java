package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "information", schema = "oywb_test", catalog = "")
public class InformationEntity {
    private long id;
    private String title;
    private String welcomeMessage;
    private byte needName;
    private byte needSex;
    private byte needAge;
    private byte needSchool;
    private byte needSps;
    private byte needGrade;
    private byte needPhone;
    private byte needEmail;
    private byte needWechat;
    private String customQuestion;
    private String conclusion;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "welcome_message", nullable = false, length = 255)
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    @Basic
    @Column(name = "need_name", nullable = false)
    public byte getNeedName() {
        return needName;
    }

    public void setNeedName(byte needName) {
        this.needName = needName;
    }

    @Basic
    @Column(name = "need_sex", nullable = false)
    public byte getNeedSex() {
        return needSex;
    }

    public void setNeedSex(byte needSex) {
        this.needSex = needSex;
    }

    @Basic
    @Column(name = "need_age", nullable = false)
    public byte getNeedAge() {
        return needAge;
    }

    public void setNeedAge(byte needAge) {
        this.needAge = needAge;
    }

    @Basic
    @Column(name = "need_school", nullable = false)
    public byte getNeedSchool() {
        return needSchool;
    }

    public void setNeedSchool(byte needSchool) {
        this.needSchool = needSchool;
    }

    @Basic
    @Column(name = "need_sps", nullable = false)
    public byte getNeedSps() {
        return needSps;
    }

    public void setNeedSps(byte needSps) {
        this.needSps = needSps;
    }

    @Basic
    @Column(name = "need_grade", nullable = false)
    public byte getNeedGrade() {
        return needGrade;
    }

    public void setNeedGrade(byte needGrade) {
        this.needGrade = needGrade;
    }

    @Basic
    @Column(name = "need_phone", nullable = false)
    public byte getNeedPhone() {
        return needPhone;
    }

    public void setNeedPhone(byte needPhone) {
        this.needPhone = needPhone;
    }

    @Basic
    @Column(name = "need_email", nullable = false)
    public byte getNeedEmail() {
        return needEmail;
    }

    public void setNeedEmail(byte needEmail) {
        this.needEmail = needEmail;
    }

    @Basic
    @Column(name = "need_wechat", nullable = false)
    public byte getNeedWechat() {
        return needWechat;
    }

    public void setNeedWechat(byte needWechat) {
        this.needWechat = needWechat;
    }

    @Basic
    @Column(name = "custom_question", nullable = false, length = -1)
    public String getCustomQuestion() {
        return customQuestion;
    }

    public void setCustomQuestion(String customQuestion) {
        this.customQuestion = customQuestion;
    }

    @Basic
    @Column(name = "conclusion", nullable = false, length = -1)
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
        return id == that.id &&
                needName == that.needName &&
                needSex == that.needSex &&
                needAge == that.needAge &&
                needSchool == that.needSchool &&
                needSps == that.needSps &&
                needGrade == that.needGrade &&
                needPhone == that.needPhone &&
                needEmail == that.needEmail &&
                needWechat == that.needWechat &&
                Objects.equals(title, that.title) &&
                Objects.equals(welcomeMessage, that.welcomeMessage) &&
                Objects.equals(customQuestion, that.customQuestion) &&
                Objects.equals(conclusion, that.conclusion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, welcomeMessage, needName, needSex, needAge, needSchool, needSps, needGrade, needPhone, needEmail, needWechat, customQuestion, conclusion);
    }
}
