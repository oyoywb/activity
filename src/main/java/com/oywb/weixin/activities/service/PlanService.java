package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PlanRequestDto;
import com.oywb.weixin.activities.dto.response.PlanResponseDto;

import java.util.List;

public interface PlanService {
    void createPlan(PlanRequestDto planRequestDto, String openId) throws Exception;

    List<PlanResponseDto> getPlan(String openId) throws Exception;

    void updatePlan(PlanRequestDto planRequestDto, String openId) throws Exception;

    void deletePlan(long id) throws Exception;

    void topPlan(long id, boolean isTop) throws Exception;
}
