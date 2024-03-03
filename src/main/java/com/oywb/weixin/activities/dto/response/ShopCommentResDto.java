package com.oywb.weixin.activities.dto.response;

import com.oywb.weixin.activities.entity.ShopCommentEntity;
import lombok.Data;

@Data
public class ShopCommentResDto {
    private ShopCommentEntity shopCommentEntity;

    private String profile;

    private String name;
}
