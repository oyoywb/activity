package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ProjectRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {
    CommonResponse createProject(ProjectRequestDto projectRequestDto, List<MultipartFile> files, String openId) throws Exception;

    CommonResponse getProjects(Pageable pageable) throws Exception;

    CommonResponse getProjectDetail(Long id) throws Exception;


    CommonResponse signup(long projectId, String openId);
}
