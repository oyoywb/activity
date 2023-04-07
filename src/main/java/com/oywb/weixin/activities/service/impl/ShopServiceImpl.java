package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.dao.SellerRepository;
import com.oywb.weixin.activities.dao.ShopCommentRepository;
import com.oywb.weixin.activities.dao.ShopRepository;
import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.SellerRequestDto;
import com.oywb.weixin.activities.dto.request.ShopCommentRequestDto;
import com.oywb.weixin.activities.dto.request.ShopRequestDto;
import com.oywb.weixin.activities.dto.response.SellerResponseDto;
import com.oywb.weixin.activities.dto.response.ShopCommentResponseDto;
import com.oywb.weixin.activities.dto.response.ShopSimpleDto;
import com.oywb.weixin.activities.entity.SellerEntity;
import com.oywb.weixin.activities.entity.ShopCommentEntity;
import com.oywb.weixin.activities.entity.ShopEntity;
import com.oywb.weixin.activities.entity.UserEntity;
import com.oywb.weixin.activities.service.ShopService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    private final SellerRepository sellerRepository;

    private final ShopCommentRepository shopCommentRepository;

    private final UserRepository userRepository;

    private final EntityManager entityManager;

    private final Minio minio;

    private final MinioConfig minioConfig;

    public ShopServiceImpl(ShopRepository shopRepository, SellerRepository sellerRepository, ShopCommentRepository shopCommentRepository, UserRepository userRepository, EntityManager entityManager, Minio minio, MinioConfig minioConfig) {
        this.shopRepository = shopRepository;
        this.sellerRepository = sellerRepository;
        this.shopCommentRepository = shopCommentRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.minio = minio;
        this.minioConfig = minioConfig;
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

        shopRequestDto.getPicture().forEach(picture -> {
            picture = minioConfig.getEndpoint() + "/" + "shop/" + picture;
        });
        shopEntity.setPicture(String.join(",", shopRequestDto.getPicture()));

        shopEntity.setCreateTs(new Timestamp(System.currentTimeMillis()));
        shopEntity.setUpdateTs(new Timestamp(System.currentTimeMillis()));
        shopEntity = shopRepository.saveAndFlush(shopEntity);

        for(SellerRequestDto sellerRequestDto : shopRequestDto.getSellerRequestDtoS()) {
            SellerEntity sellerEntity = sellerRequestDto.toSellerEntity();
            sellerEntity.setCreateTs(new Timestamp(System.currentTimeMillis()));
            sellerEntity.setUpdateTs(new Timestamp(System.currentTimeMillis()));
            sellerEntity.setShopId(shopEntity.getId());

            sellerRequestDto.getPicture().forEach( picture -> {
                picture = minioConfig.getEndpoint() + "/" + "seller/" + picture;
            });
            sellerEntity.setPicture(String.join(",", sellerRequestDto.getPicture()));

            sellerRepository.save(sellerEntity);
        }

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("create shop success")
                .data(shopEntity)
                .build();
    }

    @Override
    @Transactional
    public CommonResponse updateShop(ShopRequestDto shopRequestDto, List<MultipartFile> files) throws Exception {
        if (shopRequestDto.getId() != 0) {
            Optional<ShopEntity> shopEntityOptional = shopRepository.findById(shopRequestDto.getId());

            if (shopEntityOptional.isPresent()) {
                ShopEntity shopEntity = shopEntityOptional.get();
                shopRequestDto.updateShopEntity(shopEntity);

                files.forEach(file -> {
                    String fileName = file.getOriginalFilename();
                    if (shopRequestDto.getPicture().contains(fileName)) {
                        minio.upload(fileName, "shop", file);
                    } else {
                        minio.upload(fileName, "seller", file);
                    }
                });

                shopRequestDto.getPicture().forEach(picture -> {
                    picture = minioConfig.getEndpoint() + "/" + "shop/" + picture;
                });
                shopEntity.setPicture(String.join(",", shopRequestDto.getPicture()));
                shopEntity.setUpdateTs(new Timestamp(System.currentTimeMillis()));
                shopEntity.setPass(0);

                shopRepository.saveAndFlush(shopEntity);

                //exist id
                List<Long> sellerId =  shopRequestDto.getSellerRequestDtoS().stream().map(sellerRequestDto -> sellerRequestDto.getId())
                        .collect(Collectors.toList());

                List<SellerEntity> sellerEntities = sellerRepository.findAllByShopId(shopEntity.getId());
                sellerEntities.forEach(sellerEntity -> {
                    if (!sellerId.contains(sellerEntity.getId())) {
                        sellerRepository.deleteById(sellerEntity.getId());
                    }
                });

                for(SellerRequestDto sellerRequestDto : shopRequestDto.getSellerRequestDtoS()) {
                    SellerEntity sellerEntity = sellerRequestDto.toSellerEntity();
                    if (sellerRequestDto.getId() == 0l) {
                        sellerEntity.setCreateTs(new Timestamp(System.currentTimeMillis()));
                    }
                    sellerEntity.setUpdateTs(new Timestamp(System.currentTimeMillis()));
                    sellerEntity.setShopId(shopEntity.getId());

                    sellerRequestDto.getPicture().forEach( picture -> {
                        picture = minioConfig.getEndpoint() + "/" + "seller/" + picture;
                    });
                    sellerEntity.setPicture(String.join(",", sellerRequestDto.getPicture()));

                    sellerRepository.save(sellerEntity);
                }

                return CommonResponse.builder().code(HttpStatus.OK.value())
                        .message("create shop success")
                        .data(shopEntity)
                        .build();
            } else {
                throw new Exception("shop do not exist");
            }
        }
        throw new Exception("shop do not exist");
    }

    @Override
    public CommonResponse createComment(ShopCommentRequestDto shopCommentRequestDto, List<MultipartFile> files) throws Exception {
        ShopCommentEntity shopCommentEntity = shopCommentRequestDto.toShopCommentEntity();

        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            minio.upload(fileName, "shopComment", file);
        });

        shopCommentRequestDto.getPicture().forEach( picture -> {
            picture = minioConfig.getEndpoint() + "/" + "shopComment/" + picture;
        });

        shopCommentEntity.setPicture(String.join(",", shopCommentRequestDto.getPicture()));
        shopCommentRepository.save(shopCommentEntity);

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("create shop comment success")
                .data(shopCommentEntity)
                .build();
    }

    public CommonResponse getShopSimple(Long userId, String school, String zone
            , String type, Pageable pageable) throws Exception {

        StringBuffer sql = new StringBuffer("SELECT shop.id, shop.user_id, shop.school, shop.zone , shop.name, AVG(shop_comment.score) AS score, shop.type, shop.conditions, shop.status, shop.location FROM shop LEFT JOIN shop_comment ON shop.id = shop_comment.shop_id WHERE 1=1");
        Optional.ofNullable(userId)
                .ifPresent(value -> {
                    sql.append(" and shop.user_id = ").append(value);
                });

        Optional.ofNullable(school)
                .ifPresent(value -> {
                    sql.append(" and shop.school = ").append(value);
                });

        Optional.ofNullable(zone)
                .ifPresent(value -> {
                    sql.append(" and shop.zone = ").append(value);
                });

        Optional.ofNullable(type)
                .ifPresent(value -> {
                    sql.append(" and shop.type = ").append(value);
                });

        sql.append(" GROUP BY shop.id");
        Query query = entityManager.createNativeQuery(sql.toString());
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());

        List<ShopSimpleDto> simpleDtoS = query.getResultList();

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("")
                .data(new PageImpl<>(simpleDtoS, PageRequest.of(pageable.getPageNumber(), pageable.getPageNumber()), simpleDtoS.size())).build();

    }

    @Override
    public CommonResponse getSeller(Long shopId) throws Exception {
        List<SellerEntity> sellerEntities = sellerRepository.findAllByShopId(shopId);

        List<SellerResponseDto> sellerResponseDtos = sellerEntities.stream().map(sellerEntity -> sellerEntity.toSellerResponseDto())
                .collect(Collectors.toList());


        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("")
                .data(sellerResponseDtos).build();
    }

    @Override
    public CommonResponse getComments(Long shopId) throws Exception {
        List<ShopCommentEntity> shopCommentEntities = shopCommentRepository.getShopCommentEntitiesByShopId(shopId);

        List<ShopCommentResponseDto> shopCommentResponseDtoS = new ArrayList<>();

        shopCommentEntities.forEach(shopCommentEntity -> {
            Optional<UserEntity> userEntity = userRepository.findById(shopCommentEntity.getUserId());

            if (userEntity.isPresent()) {
                ShopCommentResponseDto shopCommentResponseDto = new ShopCommentResponseDto();
                shopCommentResponseDto.setTs(shopCommentEntity.getTs());
                shopCommentResponseDto.setScore(shopCommentEntity.getScore());
                shopCommentResponseDto.setContent(shopCommentEntity.getContent());
                shopCommentResponseDto.setUserId(shopCommentEntity.getUserId());
                shopCommentResponseDto.setProfile(userEntity.get().getProfile());
                shopCommentResponseDto.setId(shopCommentEntity.getId());
                shopCommentResponseDto.setPicture(Arrays.asList(shopCommentEntity.getPicture().split(",")));
                shopCommentResponseDtoS.add(shopCommentResponseDto);
            }
        });

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .message("")
                .data(shopCommentResponseDtoS)
                .build();
    }

}
