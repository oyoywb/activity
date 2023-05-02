package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.ShopCommentEntity;
import lombok.Data;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.util.List;

@Data
public class ShopCommentRequestDto {
    private long id;

    private long userId;

    private long shopId;

    private long sellerId;

    private int score;

    private String content;

    private List<String> picture;

    public ShopCommentEntity toShopCommentEntity() {
        ShopCommentEntity shopCommentEntity = new ShopCommentEntity();

        shopCommentEntity.setShopId(this.shopId);
        shopCommentEntity.setSellerId(this.sellerId);
        shopCommentEntity.setScore(this.score);
        shopCommentEntity.setContent(this.content);

        return shopCommentEntity;

    }
}
