package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ProjectRequestDto;
import com.oywb.weixin.activities.dto.response.ProjectResponseDto;
import com.oywb.weixin.activities.entity.ProjectEntity;
import com.oywb.weixin.activities.entity.ProjectSimpleEntity;
import com.oywb.weixin.activities.entity.ResumeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.util.List;

public interface ProjectService {
    void createProject(ProjectRequestDto projectRequestDto, StandardMultipartHttpServletRequest req, String openId) throws Exception;

    Page<ProjectSimpleEntity> getProjects(Pageable pageable, int flag, String openId, byte pass, String name) throws Exception;

    ProjectResponseDto getProjectDetail(Long id) throws Exception;


    CommonResponse signup(long projectId, String openId);

    List<ProjectEntity> getSelfProject(String openId, byte passed);

    void signDown(String openId, long projectId);

    List<ResumeEntity> getResumes(long projectId, byte pass);

    CommonResponse resumePass(List<Long> userIds, long projectId, byte flag);

    List<ProjectEntity> getSelfSignProject(String openId);

    void updateProject(ProjectRequestDto projectRequestDto, List<MultipartFile> files);

    void passProject(List<Long> ids, byte pass);
}
