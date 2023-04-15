package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.sql.Timestamp;

@Data
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
    private long id;
    private String name;
    private String location;
    private int count;
    private String signCount;
    private String tag;
    private Timestamp end;
}
