package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ResumeRequestDto;
import com.oywb.weixin.activities.entity.ResumeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResumeService {
    CommonResponse getResumeByUserId (String openId) throws Exception;

    CommonResponse save(ResumeRequestDto resumeRequestDto, MultipartFile file, String openId) throws Exception;

    CommonResponse getResumesByFilter(String school, String college, String subject, String grade, Pageable pageable) throws Exception;


}
