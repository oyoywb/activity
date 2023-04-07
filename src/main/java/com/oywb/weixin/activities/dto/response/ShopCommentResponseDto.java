package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ShopCommentResponseDto {

    private long id;

    private String content;
    private double score;
    private Timestamp ts;

    private long userId;

    private List<String> picture;

    private String profile;
}
