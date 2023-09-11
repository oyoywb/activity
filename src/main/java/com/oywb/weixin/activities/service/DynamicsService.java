package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.DyCommentReqDto;
import com.oywb.weixin.activities.dto.request.DynamicsRequestDto;
import com.oywb.weixin.activities.entity.DyCommentSimple;
import com.oywb.weixin.activities.entity.DynamicsCommentEntity;
import com.oywb.weixin.activities.entity.DynamicsSimpleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DynamicsService {
    void createDynamics(DynamicsRequestDto dynamicsRequestDto, List<MultipartFile> files, String openId);

    Page<DynamicsSimpleEntity> getDynamics(Pageable pageable, String tag, String openId, boolean personal);

    void likes(String openId, boolean likes, long id);

    List<DyCommentSimple> getComment(long id);

    void createComment(String openId, DyCommentReqDto dyCommentReqDto);

    void deleteDynamics(String openId, long id);

    void deleteDynamicsComment(long dyCommentId);

    List<DynamicsCommentEntity> getCommentReceive(String openId);

    List<DynamicsCommentEntity> getCommentMyself(String openId);

    void deleteDynamicsByAdmin(List<Long> ids);
}
