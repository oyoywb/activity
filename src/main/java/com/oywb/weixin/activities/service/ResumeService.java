package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ResumeRequestDto;
import com.oywb.weixin.activities.entity.ResumeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ResumeService {
    public CommonResponse getResumeByUserId (long userId) throws Exception;

    public CommonResponse save(ResumeRequestDto resumeRequestDto) throws Exception;

    public CommonResponse getResumesByFilter(String school, String college, String subject, String grade, Pageable pageable) throws Exception;
}
