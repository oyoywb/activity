package com.oywb.weixin.activities.dto.request;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DynamicsRequestDto {
    private int id;

    private String content;

    private List<byte[]> picture;

    private List<String> keyword;

    private boolean provided;

    private boolean pass;

    //是否匿名
    private boolean anonymous;

    private Timestamp createTs;

}
