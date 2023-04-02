/*
package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.config.CustomConfig;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ActivityPlanRequestDto;
import com.oywb.weixin.activities.dto.request.ActivityRequestDto;
import com.oywb.weixin.activities.dto.request.InformationDetailRequestDto;
import com.oywb.weixin.activities.dto.response.ActivityResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @PostMapping()
    public CommonResponse createActivity(@RequestBody ActivityRequestDto activityRequestDto) {
        return new CommonResponse();
    }

    @GetMapping()
    public CommonResponse<List<ActivityResponseDto>> getActivity(@RequestParam String school, @RequestParam String campus) {
        return new CommonResponse();
    }

    //報名
    @PostMapping("/signup")
    public CommonResponse signup(@RequestBody InformationDetailRequestDto informationDetailRequestDto) {
        return new CommonResponse();
    }

    @PostMapping("/plan")
    public CommonResponse addActivityToPlan(@RequestBody ActivityPlanRequestDto activityPlanRequestDto) {
        return new CommonResponse();
    }
}
*/
