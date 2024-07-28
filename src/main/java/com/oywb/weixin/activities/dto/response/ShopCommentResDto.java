package com.oywb.weixin.activities.dto.response;

import com.oywb.weixin.activities.entity.ShopCommentEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shop_comment", schema = "oywb_test", catalog = "")
public class ShopCommentResDto extends ShopCommentEntity {

    private String profile;

    private String name;
}
