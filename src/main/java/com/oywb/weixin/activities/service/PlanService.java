package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PlanRequestDto;

public interface PlanService {
    CommonResponse createPlan(PlanRequestDto planRequestDto) throws Exception;

    CommonResponse getPlan(long userId) throws Exception;

    CommonResponse updatePlan(PlanRequestDto planRequestDto) throws Exception;

    CommonResponse deletePlan(long id, long userId) throws Exception;

    CommonResponse topPlan(long id, long userId, boolean isTop) throws Exception;
}
