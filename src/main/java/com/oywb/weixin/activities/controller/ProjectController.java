package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ProjectRequestDto;
import com.oywb.weixin.activities.dto.request.ProjectResumeRequestDto;
import com.oywb.weixin.activities.dto.response.ProjectResponseDto;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @PostMapping()
    public CommonResponse createProject(@RequestBody ProjectRequestDto projectRequestDto) {
        return new CommonResponse();
    }

    @GetMapping()
    public CommonResponse<List<ProjectResponseDto>> getProjects() {
        return new CommonResponse<>();
    }

    @PostMapping("signup")
    public CommonResponse signup(@RequestBody ProjectResumeRequestDto projectResumeRequestDto) {
        return new CommonResponse<>();
    }

}
