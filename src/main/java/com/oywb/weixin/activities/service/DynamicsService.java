package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.DyCommentReqDto;
import com.oywb.weixin.activities.dto.request.DynamicsRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DynamicsService {
    CommonResponse createDynamics(DynamicsRequestDto dynamicsRequestDto, List<MultipartFile> files, String openId);

    CommonResponse getDynamics(Pageable pageable, String tag, String openId);

    CommonResponse likes(String openId, boolean likes, long id);

    CommonResponse getComment(long id);

    CommonResponse createComment(String openId, DyCommentReqDto dyCommentReqDto);
}
