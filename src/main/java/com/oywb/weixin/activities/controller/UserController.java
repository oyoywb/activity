package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PersonalInfoDto;
import com.oywb.weixin.activities.dto.request.ResumeRequestDto;
import com.oywb.weixin.activities.dto.request.UserRequestDto;
import com.oywb.weixin.activities.dto.response.ResumeResponseDto;
import com.oywb.weixin.activities.dto.response.UserResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    @PostMapping("/auth")
    public CommonResponse auth (@RequestBody PersonalInfoDto personalInfoDto) {
        return new CommonResponse();
    }

    @PostMapping("/resume")
    public CommonResponse createProfile (@RequestBody ResumeRequestDto profileDto) {
        return new CommonResponse();
    }

    //todo 認證
    @GetMapping("/resume")
    public CommonResponse<ResumeResponseDto> getResumeById (@RequestParam String userId) {
        return new CommonResponse<>();
    }

    //todo 原型圖中沒畫出篩選條件
    @GetMapping("/resumes/")
    public CommonResponse<List<ResumeRequestDto>> getResumes () {
        return new CommonResponse<>();
    }

    @GetMapping()
    public CommonResponse<UserResponseDto> getUserInfo (@RequestParam String userId) {
        return new CommonResponse<>();
    }

    @PatchMapping()
    public CommonResponse updateUserInfo (@RequestBody UserRequestDto userRequestDto) {
        return new CommonResponse<>();
    }
}
