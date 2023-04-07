package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import javax.persistence.*;
import javax.print.DocFlavor;

@Data
@SqlResultSetMapping(name = "ShopSimpleDto",
        entities = @EntityResult(entityClass = ShopSimpleDto.class,
        fields = {
                @FieldResult(name = "id", column = "id"),
                @FieldResult(name = "userId", column = "user_id"),
                @FieldResult(name = "school", column = "school"),
                @FieldResult(name = "zone", column = "zone"),
                @FieldResult(name = "name", column = "name"),
                @FieldResult(name = "score", column = "score"),
                @FieldResult(name = "type", column = "type"),
                @FieldResult(name = "condition", column = "condition"),
                @FieldResult(name = "status", column = "status"),
                @FieldResult(name = "location", column = "location")
        })
)
public class ShopSimpleDto {
    private long id;

    private long userId;

    private String school;

    private String zone; //校区

    private String name;

    private double score;

    private String type;

    private String condition;

    private int status;

    private String location;
}
