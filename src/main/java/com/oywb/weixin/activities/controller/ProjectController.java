package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ProjectRequestDto;
import com.oywb.weixin.activities.service.ProjectService;
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

    @PostMapping()
    public CommonResponse createProject(@ModelAttribute ProjectRequestDto projectRequestDto, Authentication authentication, @RequestParam(value = "files", required = false) MultipartFile[] files) throws Exception {
        return projectService.createProject(projectRequestDto, Arrays.asList(files), authentication.getName());
    }

    //如果flag是1，则获取自己发布的project
    @GetMapping()
    public CommonResponse getProjects(Pageable pageable, int flag, Authentication authentication) throws Exception {
        return projectService.getProjects(pageable, flag, authentication.getName());
    }

    @GetMapping("/detail")
    public CommonResponse getProjectDetail(Long id) throws Exception {
        return projectService.getProjectDetail(id);
    }

    @PostMapping("/signup")
    public CommonResponse signup(long projectId, Authentication authentication) {
        return projectService.signup(projectId, authentication.getName());
    }

    @GetMapping("/self")
    public CommonResponse getSelfProject(Authentication authentication, byte passed) {
        return projectService.getSelfProject(authentication.getName(), passed);
    }

    @GetMapping("/self/sign")
    public CommonResponse getSelfSignProject(Authentication authentication) {
        return projectService.getSelfSignProject(authentication.getName());
    }

    @PostMapping("/signDown")
    public CommonResponse signDown(Authentication authentication, long projectId) {
        return projectService.signDown(authentication.getName(), projectId);
    }

    @PreAuthorize("@roleEvaluator.projectBelongToUser(authentication, projectId)")
    @GetMapping("/resume")
    public CommonResponse getResume(long projectId, byte pass) {
        return projectService.getResumes(projectId, pass);
    }

    @PreAuthorize("@roleEvaluator.projectBelongToUser(authentication, projectId)")
    @PatchMapping("/resume")
    public CommonResponse resumePass(List<Long> userIds, long projectId) {
        return projectService.resumePass(userIds, projectId);
    }
}
