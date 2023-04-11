package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;

@Data
@SqlResultSetMapping(name = "ActivitySimpleEntity",
    entities = @EntityResult(entityClass = ActivitySimpleEntity.class,
    fields = {
            @FieldResult(name = "id", column = "id"),
            @FieldResult(name = "title", column = "title"),
            @FieldResult(name = "content", column = "content"),
            @FieldResult(name = "count", column = "count"),
            @FieldResult(name = "userId", column = "user_id"),
            @FieldResult(name = "profile", column = "profile"),
            @FieldResult(name = "location", column = "location")
    })
)
public class ActivitySimpleEntity {
    private long id;

    private String title;

    private String content;

    private String reaper;

    private int count;

    private long userId;

    private String profile;

    private String location;
}
