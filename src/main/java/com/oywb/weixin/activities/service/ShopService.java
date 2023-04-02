package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ShopRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ShopService {
    public CommonResponse createShop(ShopRequestDto shopRequestDto, List<MultipartFile> files) throws Exception;
}
