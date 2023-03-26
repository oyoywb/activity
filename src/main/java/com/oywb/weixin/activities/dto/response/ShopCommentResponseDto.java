package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShopCommentResponseDto {

    private String content;
    private double score;
    private Timestamp ts;

    private int userId;

    private byte[] profile;
}
