package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ActivityRequestDto;
import com.oywb.weixin.activities.dto.request.InformationDetailRequestDto;
import com.oywb.weixin.activities.dto.response.ActivityResponseDto;
import com.oywb.weixin.activities.dto.response.ActivitySimpleDto;
import com.oywb.weixin.activities.entity.ActivityEntity;
import com.oywb.weixin.activities.entity.ActivitySimpleEntity;
import com.oywb.weixin.activities.entity.InformationDetailEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

public interface ActivityService {
    void createActivity(ActivityRequestDto activityRequestDto, List<MultipartFile> files, String openId) throws Exception;

    List<ActivitySimpleDto> getActivitiesSimple(String school, String campus, Timestamp start, Timestamp end, int flag, String openId, byte verified) throws Exception;

    ActivityResponseDto getActivityDetail(long id) throws Exception;

    void signup(InformationDetailRequestDto informationDetailRequestDto, String openId) throws Exception;

    void addToPlan(long activityId, String openId) throws Exception;

    List<ActivityEntity> getSelfActivity(String openId, byte flag);

    void signDown(String openId, long activityId);

    List<InformationDetailEntity> getInformationDetails(long activityId, byte flag);

    CommonResponse activePass(List<Long> ids, long activityId, byte flag);

    List<ActivityEntity> getSelfSignActivity(String openId);

    void updateActivity(ActivityRequestDto activityRequestDto, List<MultipartFile> files);

    void verifiedActivity(List<Long> ids, byte verified);
}
