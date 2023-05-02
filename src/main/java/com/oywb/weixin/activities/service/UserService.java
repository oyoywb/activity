package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PersonalInfoDto;
import com.oywb.weixin.activities.dto.request.UserRequestDto;
import com.oywb.weixin.activities.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    void auth(PersonalInfoDto personalInfoDto, MultipartFile file, String openId) throws Exception;

    void authByAdmin(List<String> userIds) throws Exception;

    void updateUserInfo(UserRequestDto userRequestDto, MultipartFile file, String openId) throws Exception;

    Long getUserId(String openId);

    UserEntity findByOpenid(String openId);
    Page<UserEntity> getAllUser(Pageable pageable, byte type);
}
