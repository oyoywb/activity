package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ActivityRequestDto;
import com.oywb.weixin.activities.dto.request.InformationDetailRequestDto;
import com.oywb.weixin.activities.dto.response.ActivityResponseDto;
import com.oywb.weixin.activities.dto.response.ActivitySimpleDto;
import com.oywb.weixin.activities.entity.ActivityEntity;
import com.oywb.weixin.activities.entity.ActivityEntityNew;
import com.oywb.weixin.activities.entity.InformationDetailEntity;
import com.oywb.weixin.activities.service.ActivityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/activity")
@PreAuthorize("@roleEvaluator.isRegistered(authentication)")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    //tested
    @PostMapping()
    public void createActivity(@ModelAttribute ActivityRequestDto activityRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files, Authentication authentication) throws Exception {
        activityService.createActivity(activityRequestDto, Arrays.asList(files), authentication.getName());
    }

    //tested
    @PreAuthorize("@roleEvaluator.activityBelongToUser(authentication, #activityRequestDto.id)")
    @PatchMapping
    public void updateActivity(@ModelAttribute ActivityRequestDto activityRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files) {
        activityService.updateActivity(activityRequestDto, Arrays.asList(files));
    }

    //tested
    @PreAuthorize("@roleEvaluator.canGetNoPass(authentication, #verified)")
    @GetMapping()
    public List<ActivitySimpleDto> getActivitiesSimple(@RequestParam String school, @RequestParam String campus, @RequestParam Timestamp start, @RequestParam Timestamp end, int flag, Authentication authentication, byte verified) throws Exception {
        return activityService.getActivitiesSimple(school, campus, start, end, flag, authentication.getName(), verified);
    }

    //tested
    @GetMapping("/detail")
    public ActivityResponseDto getActivityDetail(long id) throws Exception {
        return activityService.getActivityDetail(id);
    }

    //tested
    //flag 取值有-1,0,1 -1没通过的,0审核中的,1通过的
    @GetMapping("/self")
    public List<ActivityEntity> getSelfActivity(Authentication authentication, byte passed) {
        return activityService.getSelfActivity(authentication.getName(), passed);
    }

    //tested
    @GetMapping("/self/sign")
    public List<ActivityEntityNew> getSelfSignActivity(Authentication authentication) {
        return activityService.getSelfSignActivity(authentication.getName());
    }

    //tested
    @DeleteMapping("/signDown")
    public void signDown(Authentication authentication, long activityId) {
        activityService.signDown(authentication.getName(), activityId);
    }

    //tested
    //報名
    @PostMapping("/signup")
    public CommonResponse signup(@RequestBody InformationDetailRequestDto informationDetailRequestDto, Authentication authentication) throws Exception {
        return activityService.signup(informationDetailRequestDto, authentication.getName());
    }

    //tested
    @PostMapping("/plan")
    public void addActivityToPlan(long activityId, Authentication authentication) throws Exception {
        activityService.addToPlan(activityId, authentication.getName());
    }

    //1 pass, 0 no vertify, -1 no pass
    @PreAuthorize("@roleEvaluator.activityBelongToUser(authentication, #activityId)")
    @GetMapping("/information")
    public List<InformationDetailEntity> getInformationDetails(long activityId, byte flag) {
        return activityService.getInformationDetails(activityId, flag);
    }

    //tested
    @PreAuthorize("@roleEvaluator.activityBelongToUser(authentication, #activityId)")
    @PatchMapping("/information")
    public CommonResponse activityPass(@RequestParam("ids") List<Long> ids, long activityId, byte flag) {
        return activityService.activePass(ids, activityId, flag);
    }

    @PutMapping("/auth")
    @PreAuthorize("@roleEvaluator.isAdmin(authentication)")
    public void verifiedActivity(@RequestParam ("ids") List<Long> ids, byte verified) {
        activityService.verifiedActivity(ids, verified);
    }
}
