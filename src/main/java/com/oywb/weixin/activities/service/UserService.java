package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PersonalInfoDto;
import com.oywb.weixin.activities.dto.request.UserRequestDto;

import java.util.List;

public interface UserService {
    public CommonResponse auth(PersonalInfoDto personalInfoDto) throws Exception;

    public CommonResponse authByAdmin(List<String> userIds) throws Exception;

    public CommonResponse updateUserInfo(UserRequestDto userRequestDto) throws Exception;
}
