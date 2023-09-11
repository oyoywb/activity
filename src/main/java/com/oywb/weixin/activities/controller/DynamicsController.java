package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.DyCommentReqDto;
import com.oywb.weixin.activities.dto.request.DynamicsRequestDto;
import com.oywb.weixin.activities.dto.response.DynamicsResponseDto;
import com.oywb.weixin.activities.entity.DyCommentSimple;
import com.oywb.weixin.activities.entity.DynamicsCommentEntity;
import com.oywb.weixin.activities.entity.DynamicsSimpleEntity;
import com.oywb.weixin.activities.service.DynamicsService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
    @RequestMapping("/dynamics")
public class DynamicsController {

    private final DynamicsService dynamicsService;

    public DynamicsController(DynamicsService dynamicsService) {
        this.dynamicsService = dynamicsService;
    }

    //tested
    @PostMapping
    public void createDynamics(@ModelAttribute DynamicsRequestDto dynamicsRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files, Authentication authentication) {
        dynamicsService.createDynamics(dynamicsRequestDto, Arrays.asList(files), authentication.getName());
    }

    //tested
    @GetMapping
    public Page<DynamicsSimpleEntity> getDynamics(Pageable pageable, @RequestParam(required = false) String tag, Authentication authentication, @RequestParam(required = false) boolean personal) {
        return dynamicsService.getDynamics(pageable, tag, authentication.getName(), personal);
    }
    //tested
    @DeleteMapping
    public void deleteDynamics(Authentication authentication, long id) {
        dynamicsService.deleteDynamics(authentication.getName(), id);
    }

    @PreAuthorize("@roleEvaluator.isAdmin(authentication)")
    @DeleteMapping
    public void deleteDynamicsByAdmin(List<Long> ids) {
        dynamicsService.deleteDynamicsByAdmin(ids);
    }

    //tested
    @PatchMapping("/likes")
    public void likes(Authentication authentication, boolean like, long id) {
        dynamicsService.likes(authentication.getName(), like, id);
    }

    //tested
    //获取活动的评论
    @GetMapping("/comment")
    public List<DyCommentSimple> getComment(long id) {
        return dynamicsService.getComment(id);
    }

    //tested
    //获取收到的评论
    @GetMapping("/comment/receive")
    public List<DynamicsCommentEntity> getCommentReceive (Authentication authentication) {
        return dynamicsService.getCommentReceive(authentication.getName());
    }

    @GetMapping("comment/myself")
    public List<DynamicsCommentEntity> getCommentMyself(Authentication authentication) {
        return dynamicsService.getCommentMyself(authentication.getName());
    }

    //tested
    @PostMapping("/comment")
    public void createComment(Authentication authentication, @RequestBody DyCommentReqDto dyCommentReqDto) {
        dynamicsService.createComment(authentication.getName(), dyCommentReqDto);
    }

    @PreAuthorize("@roleEvaluator.dyCommentBelongToUser(authentication, dyId, dyCommentId)")
    @DeleteMapping("/comment")
    public void deleteComment(Authentication authentication, long dyId, long dyCommentId) {
        dynamicsService.deleteDynamicsComment(dyCommentId);
    }
}
