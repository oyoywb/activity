package com.oywb.weixin.activities.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ShopRequestDto {

    private int id;
    private List<byte []> picture;

    private String name;

    private String type;

    private List<SellerRequestDto> sellerRequestDtoS;

    private String start;

    private String end;

    private List<String> conditions;

    private String location;
}
