package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@SqlResultSetMapping(name = "ActivityEntityNew",
        entities = @EntityResult(entityClass = ActivityEntityNew.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "title", column = "title"),
                        @FieldResult(name = "introduction", column = "introduction"),
                        @FieldResult(name = "count", column = "count"),
                        @FieldResult(name = "userId", column = "user_id"),
                        @FieldResult(name = "location", column = "location"),
                        @FieldResult(name = "recommand", column = "recommand"),
                        @FieldResult(name = "reaper", column = "reaper"),
                        @FieldResult(name = "start", column = "start"),
                        @FieldResult(name = "end", column = "end"),
                        @FieldResult(name = "type", column = "type"),
                        @FieldResult(name = "isAddedToPlan", column = "is_added_to_plan"),
                        @FieldResult(name = "organizer", column = "organizer"),
                        @FieldResult(name = "rule", column = "rule"),
                        @FieldResult(name = "form", column = "form"),
                        @FieldResult(name = "collectOrNot", column = "collect_or_not"),
                        @FieldResult(name = "verified", column = "verified"),
                        @FieldResult(name = "school", column = "school"),
                        @FieldResult(name = "campus", column = "campus"),
                        @FieldResult(name = "picture", column = "picture"),
                })
)
public class ActivityEntityNew{

    @Id
    private long id;

    private String title;

    private String type;

    private Timestamp start;

    private Timestamp end;

    private Integer count;

    private String location;

    private String organizer;

    private String introduction;

    private String rule;

    private String form;

    private Byte collectOrNot;

    private String recommand;

    private String reaper;

    private Byte verified;

    private Long userId;

    private String school;

    private String campus;

    private String picture;

    private boolean isAddedToPlan;
}
