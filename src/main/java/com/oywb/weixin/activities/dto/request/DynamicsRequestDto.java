package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.DynamicsEntity;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DynamicsRequestDto {
    private long id;

    private String content;

    private List<String> keyword;

    private boolean provided;

    private boolean pass;

    //是否匿名
    private boolean anonymous;

    private Timestamp createTs;

    private String contact;

    public DynamicsEntity toDynamicsEntity() {
        DynamicsEntity dynamicsEntity = new DynamicsEntity();
        dynamicsEntity.setContent(this.content);
        dynamicsEntity.setKeyword(String.join(",", this.keyword));
        dynamicsEntity.setAnonymous((byte) (anonymous ? 1 : 0));
        dynamicsEntity.setProvided((byte) (provided ? 1 : 0));
        dynamicsEntity.setContact(this.contact);
        return dynamicsEntity;
    }

}
