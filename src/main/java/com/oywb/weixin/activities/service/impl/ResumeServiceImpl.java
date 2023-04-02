package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.dao.ResumeRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ResumeRequestDto;
import com.oywb.weixin.activities.dto.response.ResumeResponseDto;
import com.oywb.weixin.activities.entity.ResumeEntity;
import com.oywb.weixin.activities.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }


    @Override
    public CommonResponse getResumeByUserId(long userId) throws Exception {
        List<ResumeEntity> resumeEntities = resumeRepository.getAllByUserId(userId);

        List<ResumeResponseDto> resumeResponseDtoS = resumeEntities.stream().map(ResumeEntity :: toResumeResponseDto)
                .collect(Collectors.toList());

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("")
                .data(resumeResponseDtoS)
                .build();
    }

    @Override
    public CommonResponse save(ResumeRequestDto resumeRequestDto) throws Exception {
        ResumeEntity resumeEntity = resumeRequestDto.toResumeEntity();
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
