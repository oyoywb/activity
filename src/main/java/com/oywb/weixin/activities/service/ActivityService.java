package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ActivityRequestDto;
import com.oywb.weixin.activities.dto.request.InformationDetailRequestDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

public interface ActivityService {
    CommonResponse createActivity(ActivityRequestDto activityRequestDto, List<MultipartFile> files) throws Exception;

    CommonResponse getActivitiesSimple(String school, String campus, Timestamp start, Timestamp end) throws Exception;

    CommonResponse getActivityDetail(long id) throws Exception;

    CommonResponse signup(InformationDetailRequestDto informationDetailRequestDto, Authentication authentication) throws Exception;

    CommonResponse addToPlan(long activityId, String openId) throws Exception;
}
