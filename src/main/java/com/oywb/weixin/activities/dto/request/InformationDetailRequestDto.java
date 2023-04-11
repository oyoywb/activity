package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.InformationDetailEntity;
import com.oywb.weixin.activities.entity.InformationEntity;
import lombok.Data;

import java.util.Map;

@Data
public class InformationDetailRequestDto {
    private long id;

    private String name;

    private Integer age;

    private Byte sex;

    private String school;

    private String sps;

    private String grade;

    private String phone;

    private String email;

    private String wechat;

    private long activityId;

    private boolean passed;

    private long userId;

    private Map<String, String> custom_question;

    public InformationDetailEntity toInformationDetailEntity() {
        InformationDetailEntity informationDetailEntity = new InformationDetailEntity();

        informationDetailEntity.setName(this.name);
        informationDetailEntity.setAge(this.age);
        informationDetailEntity.setSex(this.sex);
        informationDetailEntity.setSchool(this.school);
        informationDetailEntity.setSps(this.sps);
        informationDetailEntity.setGrade(this.grade);
        informationDetailEntity.setPhone(this.phone);
        informationDetailEntity.setEmail(this.email);
        informationDetailEntity.setWechat(this.wechat);

        informationDetailEntity.setActivityId(this.activityId);
        informationDetailEntity.setUserId(this.userId);

        return informationDetailEntity;
    }
}
