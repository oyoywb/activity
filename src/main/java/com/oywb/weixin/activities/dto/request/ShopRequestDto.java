package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.ShopEntity;
import lombok.Data;

import java.util.List;

@Data
public class ShopRequestDto {

    private long id;

    private long userId;

    private List<String> picture;

    private String name;

    private String type;

    private List<SellerRequestDto> sellerRequestDtoS;

    private String start;

    private String end;

    private List<String> conditions;

    private String location;

    private String school;

    private String zone;

    public ShopEntity toShopEntity() {
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setUserId(this.userId);
        shopEntity.setName(this.name);
        shopEntity.setType(this.type);
        shopEntity.setStart(this.start);
        shopEntity.setEnd(this.end);
        shopEntity.setLocation(this.location);
        shopEntity.setConditions(String.join(",", this.conditions));
        shopEntity.setSchool(this.school);
        shopEntity.setZone(this.zone);
        //shopEntity.setPicture(String.join(",", this.getPicture()));

        return shopEntity;
    }

    public void updateShopEntity(ShopEntity shopEntity) {
        shopEntity.setName(this.name);
        shopEntity.setType(this.type);
        shopEntity.setStart(this.start);
        shopEntity.setEnd(this.end);
        shopEntity.setLocation(this.location);
        shopEntity.setConditions(String.join(",", this.conditions));
        shopEntity.setSchool(this.school);
        shopEntity.setZone(this.zone);
    }
}
