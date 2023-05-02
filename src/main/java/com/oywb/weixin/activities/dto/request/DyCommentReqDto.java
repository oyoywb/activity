package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.DynamicsCommentEntity;
import lombok.Data;

@Data
public class DyCommentReqDto {
    private String content;
    private Long parentId;
    private long dyId;

    public DynamicsCommentEntity toDynamicsCommentEntity() {
        DynamicsCommentEntity dynamicsCommentEntity = new DynamicsCommentEntity();
        dynamicsCommentEntity.setContent(this.content);
        dynamicsCommentEntity.setParentId(this.parentId);
        dynamicsCommentEntity.setDyId(this.dyId);

        return dynamicsCommentEntity;
    }
}
