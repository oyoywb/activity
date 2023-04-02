package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.dao.SellerRepository;
import com.oywb.weixin.activities.dao.ShopRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.SellerRequestDto;
import com.oywb.weixin.activities.dto.request.ShopRequestDto;
import com.oywb.weixin.activities.entity.SellerEntity;
import com.oywb.weixin.activities.entity.ShopEntity;
import com.oywb.weixin.activities.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    private final SellerRepository sellerRepository;

    private final Minio minio;

    public ShopServiceImpl(ShopRepository shopRepository, SellerRepository sellerRepository, Minio minio) {
        this.shopRepository = shopRepository;
        this.sellerRepository = sellerRepository;
        this.minio = minio;
    }

    @Transactional
    public CommonResponse createShop(ShopRequestDto shopRequestDto, List<MultipartFile> files) throws Exception {
        ShopEntity shopEntity =shopRequestDto.toShopEntity();

        //TODO picture重名驗證
        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            if (shopRequestDto.getPicture().contains(fileName)) {
                minio.upload(fileName, "shop", file);
            } else {
                minio.upload(fileName, "seller", file);
            }
        });

        shopEntity.setCreateTs(new Timestamp(System.currentTimeMillis()));
        shopEntity.setUpdateTs(new Timestamp(System.currentTimeMillis()));
        shopEntity = shopRepository.saveAndFlush(shopEntity);

/*        for(SellerRequestDto sellerRequestDto : shopRequestDto.getSellerRequestDtoS()) {
            SellerEntity sellerEntity = sellerRequestDto.toSellerEntity();
            sellerEntity.setCreateTs(new Timestamp(System.currentTimeMillis()));
            sellerEntity.setUpdateTs(new Timestamp(System.currentTimeMillis()));
            sellerEntity.setShopId(shopEntity.getId());
            sellerRepository.save(sellerEntity);
        }*/

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("create shop success")
                .data(shopEntity)
                .build();
    }
}
