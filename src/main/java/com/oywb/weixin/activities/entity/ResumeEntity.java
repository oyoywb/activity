package com.oywb.weixin.activities.entity;

import com.oywb.weixin.activities.dto.response.ResumeResponseDto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "resume", schema = "oywb_test", catalog = "")
public class ResumeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "grade")
    private String grade;
    @Basic
    @Column(name = "college")
    private String college;
    @Basic
    @Column(name = "school")
    private String school;
    @Basic
    @Column(name = "subject")
    private String subject;
    @Basic
    @Column(name = "skill")
    private String skill;
    @Basic
    @Column(name = "experience")
    private String experience;
    @Basic
    @Column(name = "interest")
    private String interest;
    @Basic
    @Column(name = "self_introduction")
    private String selfIntroduction;
    @Basic
    @Column(name = "online")
    private Integer online;
    @Basic
    @Column(name = "create_ts")
    private Timestamp createTs;
    @Basic
    @Column(name = "update_ts")
    private Timestamp updateTs;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "avatar")
    private byte[] avatar;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public Integer getOnline() {
        return online;
    }

    public Timestamp getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Timestamp createTs) {
        this.createTs = createTs;
    }

    public Timestamp getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Timestamp updateTs) {
        this.updateTs = updateTs;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResumeEntity that = (ResumeEntity) o;
        return id == that.id && userId == that.userId && Objects.equals(name, that.name) && Objects.equals(grade, that.grade) && Objects.equals(college, that.college) && Objects.equals(subject, that.subject) && Objects.equals(skill, that.skill) && Objects.equals(experience, that.experience) && Objects.equals(interest, that.interest) && Objects.equals(selfIntroduction, that.selfIntroduction) && Objects.equals(online, that.online) && Objects.equals(createTs, that.createTs) && Objects.equals(updateTs, that.updateTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, grade, college, subject, skill, experience, interest, selfIntroduction, online, createTs, updateTs, userId);
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public ResumeResponseDto toResumeResponseDto() {
        ResumeResponseDto resumeResponseDto = new ResumeResponseDto();
        resumeResponseDto.setId(this.id);
        resumeResponseDto.setAvatar(this.avatar);
        resumeResponseDto.setName(this.name);
        resumeResponseDto.setCollege(this.college);
        resumeResponseDto.setGrade(this.grade);
        resumeResponseDto.setInterest(this.interest);
        resumeResponseDto.setOg(this.subject);
        resumeResponseDto.setUserId(this.userId);
        resumeResponseDto.setSkill(this.skill);
        resumeResponseDto.setExperience(this.experience);
        resumeResponseDto.setIntroduction(this.selfIntroduction);
        resumeResponseDto.setOnline(this.online == 1 ? true : false);
        resumeResponseDto.setSchool(this.school);

        return resumeResponseDto;
    }
}
