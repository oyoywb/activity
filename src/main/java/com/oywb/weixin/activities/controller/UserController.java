package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PersonalInfoDto;
import com.oywb.weixin.activities.dto.request.ResumeRequestDto;
import com.oywb.weixin.activities.dto.request.UserRequestDto;
import com.oywb.weixin.activities.dto.response.ResumeResponseDto;
import com.oywb.weixin.activities.entity.MessageHistoryEntity;
import com.oywb.weixin.activities.entity.MessageHistorySimpleEntity;
import com.oywb.weixin.activities.entity.ResumeEntity;
import com.oywb.weixin.activities.entity.UserEntity;
import com.oywb.weixin.activities.service.MessageHistoryService;
import com.oywb.weixin.activities.service.ResumeService;
import com.oywb.weixin.activities.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


//TODO
//1. 用户只能获取自己 修改自己的信息 不能修改他人的
@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final ResumeService resumeService;
    private final MessageHistoryService messageHistoryService;

    public UserController(UserService userService, ResumeService resumeService, MessageHistoryService messageHistoryService) {
        this.userService = userService;
        this.resumeService = resumeService;
        this.messageHistoryService = messageHistoryService;
    }

    //tested
    @PostMapping("/auth")
    public void auth (@ModelAttribute PersonalInfoDto personalInfoDto, @RequestParam(value = "file", required = false) MultipartFile[] files, Authentication authentication) throws Exception {
        userService.auth(personalInfoDto, Arrays.asList(files), authentication.getName());
    }

    //tested
    @PatchMapping()
    @PreAuthorize("@roleEvaluator.isRegistered(authentication)")
    public void updateUserInfo (@ModelAttribute UserRequestDto userRequestDto, @RequestParam(value = "file", required = false) MultipartFile file, Authentication authentication) throws Exception {
        userService.updateUserInfo(userRequestDto, file, authentication.getName());
    }

    @PatchMapping("/auth/admin")
    @PreAuthorize("@roleEvaluator.isAdmin(authentication)")
    public void authByAdmin (@RequestBody List<String> userId, byte pass) throws Exception {
        userService.authByAdmin(userId, pass);
    }

    //tested
    @PreAuthorize("@roleEvaluator.isRegistered(authentication)")
    @PostMapping("/resume")
    public void createResume (@ModelAttribute ResumeRequestDto resumeRequestDto, @RequestParam(value = "file") MultipartFile file, Authentication authentication) throws Exception {
        resumeService.save(resumeRequestDto, file, authentication.getName());
    }

    //tested
    //获取个人的简历，不用分页
    @GetMapping("/resume")
    @PreAuthorize("@roleEvaluator.isRegistered(authentication)")
    public List<ResumeResponseDto> getResumeById (Authentication authentication) throws Exception {
        return resumeService.getResumeByUserId(authentication.getName());
    }

    @PatchMapping ("/resume")
    public void updateResumeById (@ModelAttribute ResumeRequestDto resumeRequestDto, @RequestParam(value = "file") MultipartFile file, Authentication authentication) throws Exception {
        resumeService.updateResumeByUserId(resumeRequestDto, file, authentication.getName());
    }

    @PatchMapping("/resume/update")
    @PreAuthorize("@roleEvaluator.resumeBelongToUser(authentication, resumeId)")
    public void updateResumeStatus (Long resumeId, Authentication authentication, int online) {
        resumeService.updateResumeStatus(resumeId, authentication, online);
    }


    //tested
    //todo 原型圖中沒畫出篩選條件
    @GetMapping("/resumes")
    @PreAuthorize("@roleEvaluator.isRegistered(authentication)")
    public Page<ResumeEntity> getResumes (@RequestParam(required = false) String school, @RequestParam(required = false) String college
            , @RequestParam(required = false) String subject, @RequestParam(required = false) String grade, Pageable pageable, String name) throws Exception {
        return resumeService.getResumesByFilter(school, college, subject, grade, pageable, name);
    }

    //tested
    @GetMapping("/messageSimple")
    public List<MessageHistorySimpleEntity> getMessage(Authentication authentication) {
        return messageHistoryService.getMessageSimple(authentication.getName());
    }

    //tested
    @GetMapping("/messageDetail")
    public List<MessageHistoryEntity> getMessageDetail(Authentication authentication, long toUser) {
        return messageHistoryService.getMessageDetail(authentication.getName(), toUser);
    }

    @PreAuthorize("@roleEvaluator.isAdmin(authentication)")
    @GetMapping()
    public Page<UserEntity> getAllUser(Pageable pageable, byte type) {
        return userService.getAllUser(pageable, type);
    }
}
