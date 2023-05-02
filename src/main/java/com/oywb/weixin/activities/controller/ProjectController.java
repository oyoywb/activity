package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ProjectRequestDto;
import com.oywb.weixin.activities.dto.response.ProjectResponseDto;
import com.oywb.weixin.activities.entity.ProjectEntity;
import com.oywb.weixin.activities.entity.ProjectSimpleEntity;
import com.oywb.weixin.activities.entity.ResumeEntity;
import com.oywb.weixin.activities.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/project")
@PreAuthorize("@roleEvaluator.isRegistered(authentication)")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //tested
    @PostMapping()
    public void createProject(@ModelAttribute ProjectRequestDto projectRequestDto, Authentication authentication, @RequestParam(value = "files", required = false) MultipartFile[] files) throws Exception {
        projectService.createProject(projectRequestDto, Arrays.asList(files), authentication.getName());
    }

    //tested
    @PreAuthorize("@roleEvaluator.projectBelongToUser(authentication, #projectRequestDto.id)")
    @PutMapping()
    public void updateProject(@ModelAttribute ProjectRequestDto projectRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files) {
        projectService.updateProject(projectRequestDto, Arrays.asList(files));
    }

    //tested
    //如果flag是1，则获取自己发布的project
    @GetMapping()
    public Page<ProjectSimpleEntity> getProjects(Pageable pageable, int flag, Authentication authentication) throws Exception {
        return projectService.getProjects(pageable, flag, authentication.getName());
    }

    //tested
    @GetMapping("/detail")
    public ProjectResponseDto getProjectDetail(Long id) throws Exception {
        return projectService.getProjectDetail(id);
    }

    //tested
    @PostMapping("/signup")
    public void signup(long projectId, Authentication authentication) {
        projectService.signup(projectId, authentication.getName());
    }

    //tested
    @GetMapping("/self")
    public List<ProjectEntity> getSelfProject(Authentication authentication, byte passed) {
        return projectService.getSelfProject(authentication.getName(), passed);
    }

    //tested
    @GetMapping("/self/sign")
    public List<ProjectEntity> getSelfSignProject(Authentication authentication) {
        return projectService.getSelfSignProject(authentication.getName());
    }

    //tested
    @PostMapping("/signDown")
    public void signDown(Authentication authentication, long projectId) {
        projectService.signDown(authentication.getName(), projectId);
    }

    //tested
    @PreAuthorize("@roleEvaluator.projectBelongToUser(authentication, #projectId)")
    @GetMapping("/resume")
    public List<ResumeEntity> getResume(long projectId, byte pass) {
        return projectService.getResumes(projectId, pass);
    }

    //tested
    @PreAuthorize("@roleEvaluator.projectBelongToUser(authentication, #projectId)")
    @PatchMapping("/resume")
    public CommonResponse resumePass(@RequestParam("userIds") List<Long> userIds, long projectId) {
        return projectService.resumePass(userIds, projectId);
    }
}
