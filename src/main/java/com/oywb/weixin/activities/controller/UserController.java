package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PersonalInfoDto;
import com.oywb.weixin.activities.dto.request.ResumeRequestDto;
import com.oywb.weixin.activities.dto.request.UserRequestDto;
import com.oywb.weixin.activities.dto.response.ResumeResponseDto;
import com.oywb.weixin.activities.dto.response.UserResponseDto;
import com.oywb.weixin.activities.service.ResumeService;
import com.oywb.weixin.activities.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    public CommonResponse auth (@RequestBody PersonalInfoDto personalInfoDto) throws Exception {
        return userService.auth(personalInfoDto);
    }

    @PatchMapping()
    public CommonResponse updateUserInfo (@RequestBody UserRequestDto userRequestDto) throws Exception {
        return userService.updateUserInfo(userRequestDto);
    }

    @PatchMapping("/auth/admin")
    public CommonResponse authByAdmin (@RequestBody List<String> userId) throws Exception {
        return userService.authByAdmin(userId);
    }



    @PostMapping("/resume")
    public CommonResponse createResume (@RequestBody ResumeRequestDto resumeRequestDto) throws Exception {
        return resumeService.save(resumeRequestDto);
    }

    //获取个人的简历，不用分页
    @GetMapping("/resume")
    public CommonResponse<List<ResumeRequestDto>> getResumeById (@RequestParam long userId) throws Exception {
        return resumeService.getResumeByUserId(userId);
    }

    //todo 原型圖中沒畫出篩選條件
    @GetMapping("/resumes")
    public CommonResponse getResumes (@RequestParam(required = false) String school, @RequestParam(required = false) String college
            , @RequestParam(required = false) String subject, @RequestParam(required = false) String grade, Pageable pageable) throws Exception {
        return resumeService.getResumesByFilter(school, college, subject, grade, pageable);
    }
}
