package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PersonalInfoDto;
import com.oywb.weixin.activities.dto.request.ResumeRequestDto;
import com.oywb.weixin.activities.dto.request.UserRequestDto;
import com.oywb.weixin.activities.service.ResumeService;
import com.oywb.weixin.activities.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


//TODO
//1. 用户只能获取自己 修改自己的信息 不能修改他人的
@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final ResumeService resumeService;

    public UserController(UserService userService, ResumeService resumeService) {
        this.userService = userService;
        this.resumeService = resumeService;
    }

    @PostMapping("/auth")
    public CommonResponse auth (@ModelAttribute PersonalInfoDto personalInfoDto, @RequestParam(value = "file", required = false) MultipartFile file, Authentication authentication) throws Exception {
        return userService.auth(personalInfoDto, file, authentication.getName());
    }

    @PatchMapping()
    @PreAuthorize("@roleEvaluator.isRegistered(authentication)")
    public CommonResponse updateUserInfo (@ModelAttribute UserRequestDto userRequestDto, @RequestParam(value = "file", required = false) MultipartFile file, Authentication authentication) throws Exception {
        return userService.updateUserInfo(userRequestDto, file, authentication.getName());
    }

    @PatchMapping("/auth/admin")
    @PreAuthorize("@roleEvaluator.isAdmin(authentication)")
    public CommonResponse authByAdmin (@RequestBody List<String> userId) throws Exception {
        return userService.authByAdmin(userId);
    }

    @PreAuthorize("@roleEvaluator.isRegistered(authentication)")
    @PostMapping("/resume")
    public CommonResponse createResume (@ModelAttribute ResumeRequestDto resumeRequestDto, @RequestParam(value = "file") MultipartFile file, Authentication authentication) throws Exception {
        return resumeService.save(resumeRequestDto, file, authentication.getName());
    }

    //获取个人的简历，不用分页
    @GetMapping("/resume")
    @PreAuthorize("@roleEvaluator.isRegistered(authentication)")
    public CommonResponse<List<ResumeRequestDto>> getResumeById (Authentication authentication) throws Exception {
        return resumeService.getResumeByUserId(authentication.getName());
    }

    //todo 原型圖中沒畫出篩選條件
    @GetMapping("/resumes")
    @PreAuthorize("@roleEvaluator.isRegistered(authentication)")
    public CommonResponse getResumes (@RequestParam(required = false) String school, @RequestParam(required = false) String college
            , @RequestParam(required = false) String subject, @RequestParam(required = false) String grade, Pageable pageable) throws Exception {
        return resumeService.getResumesByFilter(school, college, subject, grade, pageable);
    }
}
