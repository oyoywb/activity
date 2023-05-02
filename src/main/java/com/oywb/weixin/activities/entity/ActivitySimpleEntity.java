package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@SqlResultSetMapping(name = "ActivitySimpleEntity",
    entities = @EntityResult(entityClass = ActivitySimpleEntity.class,
    fields = {
            @FieldResult(name = "id", column = "id"),
            @FieldResult(name = "title", column = "title"),
            @FieldResult(name = "introduction", column = "introduction"),
            @FieldResult(name = "count", column = "count"),
            @FieldResult(name = "userId", column = "user_id"),
            @FieldResult(name = "profile", column = "profile"),
            @FieldResult(name = "location", column = "location"),
            @FieldResult(name = "recommand", column = "recommand"),
            @FieldResult(name = "reaper", column = "reaper")
    })
)
public class ActivitySimpleEntity {
    @Id
    private Long id;

    private String title;

    private String introduction;

    private String reaper;

    private int count;

    private long userId;

    private String profile;

    private String location;

    private String recommand;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
