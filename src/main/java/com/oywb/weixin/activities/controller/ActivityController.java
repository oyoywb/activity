package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ActivityRequestDto;
import com.oywb.weixin.activities.dto.request.InformationDetailRequestDto;
import com.oywb.weixin.activities.service.ActivityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Arrays;

@RestController
@RequestMapping("/activity")
@PreAuthorize("@roleEvaluator.isRegistered(authentication)")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping()
    public CommonResponse createActivity(@ModelAttribute ActivityRequestDto activityRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files, Authentication authentication) throws Exception {
        return activityService.createActivity(activityRequestDto, Arrays.asList(files), authentication.getName());
    }

    @GetMapping()
    public CommonResponse getActivitiesSimple(@RequestParam String school, @RequestParam String campus, @RequestParam Timestamp start, @RequestParam Timestamp end) throws Exception {
        return activityService.getActivitiesSimple(school, campus, start, end);
    }

    @GetMapping("/detail")
    public CommonResponse getActivityDetail(long id) throws Exception {
        return activityService.getActivityDetail(id);
    }

    //報名
    @PostMapping("/signup")
    public CommonResponse signup(@RequestBody InformationDetailRequestDto informationDetailRequestDto, Authentication authentication) throws Exception {
        return activityService.signup(informationDetailRequestDto, authentication);
    }

    @PostMapping("/plan")
    public CommonResponse addActivityToPlan(long activityId, Authentication authentication) throws Exception {
        return activityService.addToPlan(activityId, authentication.getName());
    }
}
