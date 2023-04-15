package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.sql.Timestamp;

@Data
@SqlResultSetMapping(name = "DyCommentSimple",
        entities = @EntityResult(entityClass = DyCommentSimple.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "userId", column = "user_id"),
                        @FieldResult(name = "content", column = "content"),
                        @FieldResult(name = "parentId", column = "parent_id"),
                        @FieldResult(name = "createdAt", column = "created_at"),
                        @FieldResult(name = "updatedAt", column = "updated_at"),
                        @FieldResult(name = "isDeleted", column = "is_deleted"),
                        @FieldResult(name = "deleteAt", column = "delete_at"),
                        @FieldResult(name = "isApproved", column = "is_approved"),
                        @FieldResult(name = "dyId", column = "dy_id"),
                        @FieldResult(name = "level", column = "level")
                })
)
public class DyCommentSimple {
    private long id;
    private long userId;
    private String content;
    private long parentId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean isDeleted;
    private Timestamp deleteAt;
    private boolean isApproved;
    private long dyId;
    private long level;
}
