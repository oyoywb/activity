package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.ResumeEntity;
import lombok.Data;

//簡歷
@Data
public class ResumeRequestDto {
    private int id;

    private int userId;

    private String name;

    private String grade;

    //學院
    private String college;

    //專業
    private String og;

    private String skill;

    //經歷
    private String experience;

    //興趣
    private String interest;

    private String introduction;

    private boolean online;

    private String school;

    public ResumeEntity toResumeEntity() {
        ResumeEntity resumeEntity = new ResumeEntity();
        resumeEntity.setName(this.name);
        resumeEntity.setGrade(this.grade);
        resumeEntity.setCollege(this.college);
        resumeEntity.setSubject(this.og);
        resumeEntity.setSkill(this.skill);
        resumeEntity.setExperience(this.experience);
        resumeEntity.setInterest(this.interest);
        resumeEntity.setSelfIntroduction(this.introduction);
        resumeEntity.setSchool(this.school);
        return resumeEntity;
    }
}
