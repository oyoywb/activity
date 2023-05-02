package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ShopCommentRequestDto;
import com.oywb.weixin.activities.dto.request.ShopRequestDto;
import com.oywb.weixin.activities.dto.response.SellerResponseDto;
import com.oywb.weixin.activities.dto.response.ShopCommentResponseDto;
import com.oywb.weixin.activities.dto.response.ShopSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ShopService {
    void createShop(ShopRequestDto shopRequestDto, List<MultipartFile> files, String openId) throws Exception;

    void updateShop(ShopRequestDto shopRequestDto, List<MultipartFile> files) throws Exception;

    void createComment(ShopCommentRequestDto shopCommentRequestDto, List<MultipartFile> files, String openId) throws Exception;

    Page<ShopSimpleDto> getShopSimple(String openId, String school, String zone
            , String type, Pageable pageable, int flag) throws Exception;

    List<SellerResponseDto> getSeller(Long shopId) throws Exception;

    List<ShopCommentResponseDto> getComments(Long shopId) throws Exception;
}
