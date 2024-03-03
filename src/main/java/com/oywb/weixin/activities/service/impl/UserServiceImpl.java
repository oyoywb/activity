package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.dto.request.PersonalInfoDto;
import com.oywb.weixin.activities.dto.request.UserRequestDto;
import com.oywb.weixin.activities.entity.UserEntity;
import com.oywb.weixin.activities.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Minio minio;

    private final MinioConfig minioConfig;

    private static final String AUTH_BUCKET = "scp";
    private static final String PROFILE_BUCKET = "profile";
    private Map<String, Long> userMap = new ConcurrentHashMap<>();

    public UserServiceImpl(UserRepository userRepository, Minio minio, MinioConfig minioConfig) {
        this.userRepository = userRepository;
        this.minio = minio;
        this.minioConfig = minioConfig;
    }

    @Transactional
    public UserEntity checkAndCreate (String openid) {
        UserEntity userEntity = userRepository.findByOpenid(openid);
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setOpenid(openid);
            userEntity.setRegisted(-1);
            userRepository.save(userEntity);
        }
        return userEntity;
    }


    @Override
    public void auth(PersonalInfoDto personalInfoDto, List<MultipartFile> files, String openId) throws Exception {
        Optional<UserEntity> userEntityOptional = Optional.ofNullable(userRepository.findByOpenid(openId));
        if (userEntityOptional.isPresent()) {

            List<String> fileNames = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                minio.upload(fileName, AUTH_BUCKET, file);
                fileNames.add(minioConfig.getDisplay() + "/" + AUTH_BUCKET + "/" + fileName);
            }

            UserEntity userEntity = userEntityOptional.get();
            userEntity.setName(personalInfoDto.getName());
            userEntity.setSchool(personalInfoDto.getSchool());
            userEntity.setSubject(personalInfoDto.getOg());
            userEntity.setGrade(personalInfoDto.getSubject());
            userEntity.setScp(String.join(",", fileNames));
            if (userEntity.getRegisted() == -1) {
                userEntity.setRegisted(1);
            }
            userRepository.save(userEntity);
        }
    }

    @Override
    public void authByAdmin(List<String> userIds, byte pass) throws Exception {
        userRepository.authByAdmin(userIds, pass);
    }

    @Override
    public void updateUserInfo(UserRequestDto userRequestDto, MultipartFile file, String openId) throws Exception {
        Optional<UserEntity> userEntityOptional = Optional.ofNullable(userRepository.findByOpenid(openId));
        if (userEntityOptional.isPresent()) {

            String fileName = file.getOriginalFilename();
            minio.upload(fileName, PROFILE_BUCKET, file);

            UserEntity userEntity = userEntityOptional.get();
            userEntity.setProfile(minioConfig.getDisplay() + "/" + PROFILE_BUCKET + "/" + fileName);
            userEntity.setNameFake(userRequestDto.getName());
            userEntity.setSex(userRequestDto.getSex());
            userEntity.setBirthday(Timestamp.valueOf(userRequestDto.getBirthday()));
            userEntity.setSign(userRequestDto.getSign());
            userRepository.save(userEntity);
        }
    }

    @Override
    public Long getUserId(String openId) {
        Long userId = userMap.get(openId);
        if (userId == null) {
            userId = userRepository.getUserIdByOpenId(openId);
            userMap.put(openId, userId);
        }

        return userId;
    }

    @Override
    public UserEntity findByOpenid(String openId) {
        return userRepository.findByOpenid(openId);
    }

    @Override
    public Page<UserEntity> getAllUser(Pageable pageable, byte type) {
        return userRepository.getAllByRegisted(type, pageable);
    }

}
