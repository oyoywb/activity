package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.DyCommentReqDto;
import com.oywb.weixin.activities.dto.request.DynamicsRequestDto;
import com.oywb.weixin.activities.dto.response.DynamicsResponseDto;
import com.oywb.weixin.activities.service.DynamicsService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping("/dynamics")
public class DynamicsController {

    private final DynamicsService dynamicsService;

    public DynamicsController(DynamicsService dynamicsService) {
        this.dynamicsService = dynamicsService;
    }

    @PostMapping
    public CommonResponse createDynamics(@ModelAttribute DynamicsRequestDto dynamicsRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files, Authentication authentication) {
        return dynamicsService.createDynamics(dynamicsRequestDto, Arrays.asList(files), authentication.getName());
    }

    @GetMapping
    public CommonResponse getDynamics(Pageable pageable, @RequestParam(required = false) String tag, Authentication authentication) {
        return dynamicsService.getDynamics(pageable, tag, authentication.getName());
    }

    @PatchMapping("/likes")
    public CommonResponse likes(Authentication authentication, boolean like, long id) {
        return dynamicsService.likes(authentication.getName(), like, id);
    }

    @GetMapping("/comment")
    public CommonResponse getComment(long id) {
        return dynamicsService.getComment(id);
    }

    @PostMapping("/comment")
    public CommonResponse createComment(Authentication authentication, DyCommentReqDto dyCommentReqDto) {
        return dynamicsService.createComment(authentication.getName(), dyCommentReqDto);
    }
}
