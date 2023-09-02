package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.dao.ResumeRepository;
import com.oywb.weixin.activities.dto.request.ResumeRequestDto;
import com.oywb.weixin.activities.dto.response.ResumeResponseDto;
import com.oywb.weixin.activities.entity.ResumeEntity;
import com.oywb.weixin.activities.service.ResumeService;
import com.oywb.weixin.activities.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private static final String RESUME_BUCKET = "resume";
    private final Minio minio;
    private final MinioConfig minioConfig;
    private final UserService userService;

    public ResumeServiceImpl(ResumeRepository resumeRepository, Minio minio, MinioConfig minioConfig, UserService userService) {
        this.resumeRepository = resumeRepository;
        this.minio = minio;
        this.minioConfig = minioConfig;
        this.userService = userService;
    }


    @Override
    public List<ResumeResponseDto> getResumeByUserId(String openId) throws Exception {
        List<ResumeEntity> resumeEntities = resumeRepository.getAllByUserId(userService.getUserId(openId));

        List<ResumeResponseDto> resumeResponseDtoS = resumeEntities.stream().map(ResumeEntity :: toResumeResponseDto)
                .collect(Collectors.toList());

        return resumeResponseDtoS;
    }

    @Override
    public void save(ResumeRequestDto resumeRequestDto, MultipartFile file, String openId) throws Exception {

        String fileName = file.getOriginalFilename();
        minio.upload(fileName, RESUME_BUCKET, file);

        ResumeEntity resumeEntity = resumeRequestDto.toResumeEntity();
        resumeEntity.setAvatar(minioConfig.getDisplay() + "/" + RESUME_BUCKET + "/" + fileName);
        resumeEntity.setUserId(userService.getUserId(openId));
        resumeRepository.save(resumeEntity);
    }

    @Override
    public Page<ResumeEntity> getResumesByFilter(String school, String college, String subject, String grade, Pageable pageable, String name) throws Exception {
        Page<ResumeEntity> resumeEntities =  resumeRepository.getByFilter(school, college, subject, grade, name, pageable);

        return resumeEntities;
    }

    @Override
    public void updateResumeByUserId(ResumeRequestDto resumeRequestDto, MultipartFile file, String name) throws Exception {
        Optional<ResumeEntity> resumeEntityOpt = resumeRepository.findById(resumeRequestDto.getId());

        if (resumeEntityOpt.isPresent()) {
            String fileName = file.getOriginalFilename();
            minio.upload(fileName, RESUME_BUCKET, file);

            ResumeEntity resumeEntity = resumeEntityOpt.get();
            resumeEntity.update(resumeRequestDto);
            resumeEntity.setAvatar(minioConfig.getDisplay() + "/" + RESUME_BUCKET + "/" + fileName);
            resumeRepository.save(resumeEntity);
        }

    }

    @Override
    public void updateResumeStatus(Long resumeId, Authentication authentication, int online) {
        Optional<ResumeEntity> resumeEntityOpt = resumeRepository.findById(resumeId);

        if (resumeEntityOpt.isPresent()) {
            ResumeEntity resumeEntity = resumeEntityOpt.get();
            resumeEntity.setOnline(online);
            resumeRepository.save(resumeEntity);
        }
    }


}
