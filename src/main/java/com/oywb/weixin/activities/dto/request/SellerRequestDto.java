package com.oywb.weixin.activities.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SellerRequestDto {
    private String name;

    private Float price;

    private String description;

    private List<byte []> picture;
}
