package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ShopCommentRequestDto;
import com.oywb.weixin.activities.dto.request.ShopRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ShopService {
    CommonResponse createShop(ShopRequestDto shopRequestDto, List<MultipartFile> files) throws Exception;

    CommonResponse updateShop(ShopRequestDto shopRequestDto, List<MultipartFile> files) throws Exception;

    CommonResponse createComment(ShopCommentRequestDto shopCommentRequestDto, List<MultipartFile> files) throws Exception;

    CommonResponse getShopSimple(Long userId, String school, String zone
            , String type, Pageable pageable) throws Exception;

    CommonResponse getSeller(Long shopId) throws Exception;

    CommonResponse getComments(Long shopId) throws Exception;
}
