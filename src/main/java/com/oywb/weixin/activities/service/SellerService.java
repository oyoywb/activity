package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.SellerRequestDto;

public interface SellerService {
    CommonResponse createSeller(SellerRequestDto sellerRequestDto) throws Exception;
}
