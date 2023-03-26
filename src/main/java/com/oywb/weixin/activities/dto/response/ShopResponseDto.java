package com.oywb.weixin.activities.dto.response;

import com.oywb.weixin.activities.entity.ShopCommentEntity;
import lombok.Data;
import java.util.List;

@Data
public class ShopResponseDto {
    private int id;

    private double score;

    private List<String> conditions;

    private int status;

    private String location;

    private List<SellerResponseDto> sellerResponseDtos;

    private List<ShopCommentResponseDto> shopCommentResponseDtos;

}
