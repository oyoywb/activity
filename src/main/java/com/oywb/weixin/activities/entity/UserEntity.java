package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "user")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "openid")
    private String openid;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "profile")
    private String profile;
    @Basic
    @Column(name = "scp")
    private String scp;
    @Basic
    @Column(name = "registed")
    private Integer registed;
    @Basic
    @Column(name = "sex")
    private Integer sex;
    @Basic
    @Column(name = "birthday")
    private Timestamp birthday;
    @Basic
    @Column(name = "sign")
    private String sign;
    @Basic
    @Column(name = "name_fake")
    private String nameFake;
    @Basic
    @Column(name = "school")
    private String school;
    @Basic
    @Column(name = "subject")
    private String subject;
    @Basic
    @Column(name = "grade")
    private String grade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getScp() {
        return scp;
    }

    public void setScp(String scp) {
        this.scp = scp;
    }

    public Integer getRegisted() {
        return registed;
    }

    public void setRegisted(Integer registed) {
        this.registed = registed;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNameFake() {
        return nameFake;
    }

    public void setNameFake(String nameFake) {
        this.nameFake = nameFake;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(openid, that.openid) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(profile, that.profile) && Objects.equals(scp, that.scp) && Objects.equals(registed, that.registed) && Objects.equals(sex, that.sex) && Objects.equals(birthday, that.birthday) && Objects.equals(sign, that.sign) && Objects.equals(nameFake, that.nameFake) && Objects.equals(school, that.school) && Objects.equals(subject, that.subject) && Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, openid, name, phone, registed, sex, birthday, sign, nameFake, school, subject, grade, profile, scp);
        return result;
    }
}
