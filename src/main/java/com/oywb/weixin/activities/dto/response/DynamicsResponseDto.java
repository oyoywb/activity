package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DynamicsResponseDto {
    private int id;

    private String content;

    private List<byte[]> picture;

    private List<String> keyword;

    private boolean provided;

    private boolean pass;

    //是否匿名
    private boolean anonymous;

    private Timestamp createTs;

    //點讚數
    private int likes;

    private int userId;

    private List<DynamicsCommentResponseDto> dynamicsCommentResponseDtos;
}
