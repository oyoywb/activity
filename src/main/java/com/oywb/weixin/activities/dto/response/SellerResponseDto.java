package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SellerResponseDto {
    private long id;

    private String name;

    private BigDecimal price;

    private String description;

    private List<String> picture;
}
