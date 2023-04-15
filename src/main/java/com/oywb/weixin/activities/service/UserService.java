package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PersonalInfoDto;
import com.oywb.weixin.activities.dto.request.UserRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    public CommonResponse auth(PersonalInfoDto personalInfoDto, MultipartFile file, String openId) throws Exception;

    public CommonResponse authByAdmin(List<String> userIds) throws Exception;

    public CommonResponse updateUserInfo(UserRequestDto userRequestDto, MultipartFile file, String openId) throws Exception;
}
