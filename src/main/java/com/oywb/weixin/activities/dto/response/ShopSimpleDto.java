package com.oywb.weixin.activities.dto.response;

import lombok.Data;

import javax.persistence.*;
import javax.print.DocFlavor;

@Entity
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
                @FieldResult(name = "location", column = "location"),
                @FieldResult(name = "picture", column = "picture"),
                @FieldResult(name = "start", column = "start"),
                @FieldResult(name = "end", column = "end")
        })
)
public class ShopSimpleDto {
    @Id
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

    private String picture;

    private String start;

    private String end;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
