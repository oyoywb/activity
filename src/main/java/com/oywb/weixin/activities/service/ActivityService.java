package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ActivityRequestDto;
import com.oywb.weixin.activities.dto.request.InformationDetailRequestDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

public interface ActivityService {
    CommonResponse createActivity(ActivityRequestDto activityRequestDto, List<MultipartFile> files, String openId) throws Exception;

    CommonResponse getActivitiesSimple(String school, String campus, Timestamp start, Timestamp end, int flag, String openId) throws Exception;

    CommonResponse getActivityDetail(long id) throws Exception;

    CommonResponse signup(InformationDetailRequestDto informationDetailRequestDto, Authentication authentication) throws Exception;

    CommonResponse addToPlan(long activityId, String openId) throws Exception;

    CommonResponse getSelfActivity(String openId, byte flag);

    CommonResponse signDown(String openId, long activityId);

    CommonResponse getInformationDetails(long activityId, byte flag);

    CommonResponse activePass(List<Long> ids, long activityId);

    CommonResponse getSelfSignActivity(String openId);

    CommonResponse updateActivity(ActivityRequestDto activityRequestDto, List<MultipartFile> files);
}
