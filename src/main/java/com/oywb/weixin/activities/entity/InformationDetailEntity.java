package com.oywb.weixin.activities.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "information_detail")
@DynamicUpdate
@DynamicInsert
public class InformationDetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "age")
    private Integer age;
    @Basic
    @Column(name = "sex")
    private Byte sex;
    @Basic
    @Column(name = "school")
    private String school;
    @Basic
    @Column(name = "sps")
    private String sps;
    @Basic
    @Column(name = "grade")
    private String grade;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "wechat")
    private String wechat;
    @Basic
    @Column(name = "custom_question")
    private String customQuestion;
    @Basic
    @Column(name = "activity_id")
    private Long activityId;
    @Basic
    @Column(name = "passed")
    private Byte passed;
    @Basic
    @Column(name = "user_id")
    private Long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSps() {
        return sps;
    }

    public void setSps(String sps) {
        this.sps = sps;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getCustomQuestion() {
        return customQuestion;
    }

    public void setCustomQuestion(String customQuestion) {
        this.customQuestion = customQuestion;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public byte getPassed() {
        return passed;
    }

    public void setPassed(byte passed) {
        this.passed = passed;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationDetailEntity that = (InformationDetailEntity) o;
        return id == that.id && activityId == that.activityId && passed == that.passed && userId == that.userId && Objects.equals(name, that.name) && Objects.equals(age, that.age) && Objects.equals(sex, that.sex) && Objects.equals(school, that.school) && Objects.equals(sps, that.sps) && Objects.equals(grade, that.grade) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(wechat, that.wechat) && Objects.equals(customQuestion, that.customQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sex, school, sps, grade, phone, email, wechat, customQuestion, activityId, passed, userId);
    }
}
