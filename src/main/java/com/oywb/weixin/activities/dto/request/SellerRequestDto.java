package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.SellerEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SellerRequestDto {
    private String name;

    private BigDecimal price;

    private String description;

    private List<String> picture;

    public SellerEntity toSellerEntity() {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setName(this.name);
        sellerEntity.setPrice(this.price);
        sellerEntity.setDiscription(this.description);
        sellerEntity.setPicture(String.join(",", this.picture));

        return sellerEntity;
    }
}
