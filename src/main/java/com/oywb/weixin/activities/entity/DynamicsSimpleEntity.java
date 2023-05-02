package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@SqlResultSetMapping(name = "DynamicsSimpleEntity",
        entities = @EntityResult(entityClass = DynamicsSimpleEntity.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "content", column = "content"),
                        @FieldResult(name = "picture", column = "picture"),
                        @FieldResult(name = "anonymous", column = "anonymous"),
                        @FieldResult(name = "provided", column = "provided"),
                        @FieldResult(name = "pass", column = "pass"),
                        @FieldResult(name = "likes", column = "likes"),
                        @FieldResult(name = "createTs", column = "create_ts"),
                        @FieldResult(name = "userId", column = "user_id"),
                        @FieldResult(name = "contact", column = "contact"),
                        @FieldResult(name = "commentCount", column = "count"),
                        @FieldResult(name = "isLikes", column = "is_likes")
                })
)
public class DynamicsSimpleEntity {
    @Id
    private long id;
    private String content;
    private String picture;
    private String keyword;
    private String anonymous;
    private byte provided;
    private byte pass;
    private long likes;
    private Timestamp createTs;
    private long userId;
    private String contact;
    private int commentCount;
    private byte isLikes;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
