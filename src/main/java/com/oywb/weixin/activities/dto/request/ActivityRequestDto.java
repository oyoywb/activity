package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.ActivityEntity;
import com.oywb.weixin.activities.entity.InformationEntity;
import lombok.Data;
import org.hibernate.tool.schema.extract.spi.InformationExtractor;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ActivityRequestDto {
    private long id;

    private List<String> picture;

    private String title;

    private String type;

    private Timestamp start;

    private Timestamp end;

    private int count;

    private String location;

    private String organizer;

    private String introduction;

    private String rule;

    //形式
    private String form;

    private boolean collectOrNot;

    private String recommand;

    private String reaper;

    private boolean verified;

    private long userId;

    private String school;

    private String campus;

    private InformationRequestDto informationRequestDto;

    public ActivityEntity toActivityEntity() {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setTitle(this.title);
        activityEntity.setType(this.type);
        activityEntity.setStart(this.start);
        activityEntity.setEnd(this.end);
        activityEntity.setCount(this.count);
        activityEntity.setLocation(this.location);
        activityEntity.setOrganizer(this.organizer);
        activityEntity.setIntroduction(this.introduction);
        activityEntity.setRule(this.rule);
        activityEntity.setForm(this.form);
        activityEntity.setCollectOrNot((byte) (collectOrNot ? 1 : 0));
        activityEntity.setRecommand(this.recommand);
        activityEntity.setReaper(this.reaper); //你将收获
        activityEntity.setUserId(this.userId);
        activityEntity.setSchool(this.school);
        activityEntity.setCampus(this.campus);

        return activityEntity;
    }

    public InformationEntity toInformationEntity() {
        InformationEntity informationEntity = new InformationEntity();
        informationEntity.setTitle(this.informationRequestDto.getTitle());
        informationEntity.setWelcomeMessage(this.informationRequestDto.getWelcomeMessage());
        informationEntity.setNeedName((byte) (this.informationRequestDto.isNeedName() ? 1 : 0));
        informationEntity.setNeedSex((byte) (this.informationRequestDto.isNeedSex() ? 1 : 0));
        informationEntity.setNeedAge((byte) (this.informationRequestDto.isNeedAge() ? 1 : 0));
        informationEntity.setNeedSchool((byte) (this.informationRequestDto.isNeedSchool() ? 1 : 0));
        informationEntity.setNeedSps((byte) (this.informationRequestDto.isNeedSps() ? 1 : 0));
        informationEntity.setNeedGrade((byte) (this.informationRequestDto.isNeedGrade() ? 1 : 0));
        informationEntity.setNeedPhone((byte) (this.informationRequestDto.isNeedPhone() ? 1 : 0));
        informationEntity.setNeedEmail((byte) (this.informationRequestDto.isNeedEmail() ? 1 : 0));
        informationEntity.setNeedWechat((byte) (this.informationRequestDto.isNeedWechat() ? 1 : 0));
        informationEntity.setCustomQuestion(String.join(",", this.informationRequestDto.getCustomerQuestion()));
        informationEntity.setConclusion(this.informationRequestDto.getConclusion());

        return informationEntity;
    }
}
