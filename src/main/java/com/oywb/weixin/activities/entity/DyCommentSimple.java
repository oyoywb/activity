package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
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
                        @FieldResult(name = "deleteAt", column = "deleted_at"),
                        @FieldResult(name = "isApproved", column = "is_approved"),
                        @FieldResult(name = "dyId", column = "dy_id"),
                        @FieldResult(name = "name", column = "name"),
                        @FieldResult(name = "profile", column = "profile"),
                        @FieldResult(name = "level", column = "level")
                })
)
public class DyCommentSimple {
    @Id
    private long id;
    private long userId;
    private String content;
    private Long parentId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean isDeleted;
    private Timestamp deleteAt;
    private boolean isApproved;
    private long dyId;
    private long level;
    private String name;
    private String profile;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
