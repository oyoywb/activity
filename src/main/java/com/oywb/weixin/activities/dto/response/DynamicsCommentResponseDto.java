package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DynamicsCommentResponseDto {
    private int id;

    private int userId;

    private String content;

    private int parentId;

    private Timestamp createAt;

    private Timestamp updateAt;

    private boolean isDeleted;

    private Timestamp deleteAt;

    private boolean isApproved;

    //動態id
    private int dyId;

    private int level;

}
