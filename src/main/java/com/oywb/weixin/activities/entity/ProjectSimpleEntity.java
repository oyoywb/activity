package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@SqlResultSetMapping(name = "ProjectSimpleEntity",
        entities = @EntityResult(entityClass = ProjectSimpleEntity.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "name", column = "name"),
                        @FieldResult(name = "location", column = "location"),
                        @FieldResult(name = "count", column = "count"),
                        @FieldResult(name = "signCount", column = "sign_count"),
                        @FieldResult(name = "tag", column = "tag")
                })
)
public class ProjectSimpleEntity {
    @Id
    private long id;
    private String name;
    private String location;
    private int count;
    private String signCount;
    private String tag;
    private Timestamp end;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
