package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SellerResponseDto {
    private String name;

    private Float price;

    private String description;

    private List<byte []> picture;
}
