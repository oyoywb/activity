package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resume", schema = "oywb_test", catalog = "")
public class ResumeEntity {
    private long id;
    private String name;
    private String grade;
    private String college;
    private String subject;
    private String skill;
    private String experience;
    private String interest;
    private String selfIntroduction;

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
    @Column(name = "grade", nullable = false, length = 255)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "college", nullable = false, length = 255)
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Basic
    @Column(name = "subject", nullable = false, length = 255)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "skill", nullable = false, length = 255)
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Basic
    @Column(name = "experience", nullable = false, length = 255)
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Basic
    @Column(name = "interest", nullable = false, length = 255)
    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Basic
    @Column(name = "self_introduction", nullable = false, length = -1)
    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResumeEntity that = (ResumeEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(college, that.college) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(skill, that.skill) &&
                Objects.equals(experience, that.experience) &&
                Objects.equals(interest, that.interest) &&
                Objects.equals(selfIntroduction, that.selfIntroduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, grade, college, subject, skill, experience, interest, selfIntroduction);
    }
}
