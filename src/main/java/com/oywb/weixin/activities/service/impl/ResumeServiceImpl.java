package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.dao.ResumeRepository;
import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ResumeRequestDto;
import com.oywb.weixin.activities.dto.response.ResumeResponseDto;
import com.oywb.weixin.activities.entity.ResumeEntity;
import com.oywb.weixin.activities.service.ResumeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private static final String RESUME_BUCKET = "resume";
    private final Minio minio;
    private final MinioConfig minioConfig;
    private final UserRepository userRepository;

    public ResumeServiceImpl(ResumeRepository resumeRepository, Minio minio, MinioConfig minioConfig, UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.minio = minio;
        this.minioConfig = minioConfig;
        this.userRepository = userRepository;
    }


    @Override
    public CommonResponse getResumeByUserId(String openId) throws Exception {
        List<ResumeEntity> resumeEntities = resumeRepository.getAllByUserId(userRepository.getUserIdByOpenId(openId));

        List<ResumeResponseDto> resumeResponseDtoS = resumeEntities.stream().map(ResumeEntity :: toResumeResponseDto)
                .collect(Collectors.toList());

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("")
                .data(resumeResponseDtoS)
                .build();
    }

    @Override
    public CommonResponse save(ResumeRequestDto resumeRequestDto, MultipartFile file, String openId) throws Exception {

        String fileName = file.getOriginalFilename();
        minio.upload(fileName, RESUME_BUCKET, file);

        ResumeEntity resumeEntity = resumeRequestDto.toResumeEntity();
        resumeEntity.setAvatar(minioConfig.getEndpoint() + "/" + RESUME_BUCKET + "/" + fileName);
        resumeEntity.setUserId(userRepository.getUserIdByOpenId(openId));
        resumeRepository.save(resumeEntity);

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("创建简历成功")
                .data(resumeEntity)
                .build();

    }

    @Override
    public CommonResponse getResumesByFilter(String school, String college, String subject, String grade, Pageable pageable) throws Exception {
        Page<ResumeEntity> resumeEntities =  resumeRepository.getByFilter(school, college, subject, grade, pageable);

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("")
                .data(resumeEntities)
                .build();
    }


}
