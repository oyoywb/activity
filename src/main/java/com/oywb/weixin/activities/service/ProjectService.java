package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ProjectRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {
    CommonResponse createProject(ProjectRequestDto projectRequestDto, List<MultipartFile> files, String openId) throws Exception;

    CommonResponse getProjects(Pageable pageable, int flag, String openId) throws Exception;

    CommonResponse getProjectDetail(Long id) throws Exception;


    CommonResponse signup(long projectId, String openId);

    CommonResponse getSelfProject(String openId, byte passed);

    CommonResponse signDown(String openId, long projectId);

    CommonResponse getResumes(long projectId, byte pass);

    CommonResponse resumePass(List<Long> userIds, long projectId);

    CommonResponse getSelfSignProject(String openId);

    CommonResponse updateProject(ProjectRequestDto projectRequestDto, List<MultipartFile> files);
}
