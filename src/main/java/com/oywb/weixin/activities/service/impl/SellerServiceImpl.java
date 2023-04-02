package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.dao.SellerRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.SellerRequestDto;
import com.oywb.weixin.activities.service.SellerService;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public CommonResponse createSeller(SellerRequestDto sellerRequestDto) throws Exception {
        return null;
    }
}
