package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PersonalInfoDto;
import com.oywb.weixin.activities.dto.request.UserRequestDto;
import com.oywb.weixin.activities.entity.UserEntity;
import com.oywb.weixin.activities.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserEntity checkAndCreate (String openid) {
        UserEntity userEntity = userRepository.findByOpenid(openid);
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setOpenid(openid);
            userRepository.save(userEntity);
        }
        return userEntity;
    }


    @Override
    public CommonResponse auth(PersonalInfoDto personalInfoDto) throws Exception {
        Optional<UserEntity> userEntityOptional = userRepository.findById(personalInfoDto.getUserId());
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            userEntity.setName(personalInfoDto.getName());
            userEntity.setSchool(personalInfoDto.getSchool());
            userEntity.setSubject(personalInfoDto.getSubject());
            userEntity.setGrade(personalInfoDto.getSubject());
            userEntity.setScp(personalInfoDto.getPicture());
            userRepository.save(userEntity);
        }

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("认证请求提交成功")
                .data(personalInfoDto).build();
    }

    @Override
    public CommonResponse authByAdmin(List<String> userIds) throws Exception {

        userRepository.authByAdmin(userIds);
        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("用户完成认证")
                .data(userIds).build();

    }

    @Override
    public CommonResponse updateUserInfo(UserRequestDto userRequestDto) throws Exception {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userRequestDto.getUserId());
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            userEntity.setProfile(userRequestDto.getProfile());
            userEntity.setNameFake(userRequestDto.getName());
            userEntity.setSex(userRequestDto.getSex());
            userEntity.setBirthday(new Timestamp(userRequestDto.getBirthday()));
            userEntity.setSign(userRequestDto.getSign());
            userRepository.save(userEntity);
        }
        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("认证请求提交成功")
                .data(userRequestDto).build();

    }

}
