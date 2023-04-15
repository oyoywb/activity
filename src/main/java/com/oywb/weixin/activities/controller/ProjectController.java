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

    @GetMapping()
    public CommonResponse getProjects(Pageable pageable) throws Exception {
        return projectService.getProjects(pageable);
    }

    @GetMapping("/detail")
    public CommonResponse getProjectDetail(Long id) throws Exception {
        return projectService.getProjectDetail(id);
    }

    @PostMapping("signup")
    public CommonResponse signup(long projectId, Authentication authentication) {
        return projectService.signup(projectId, authentication.getName());
    }

}
