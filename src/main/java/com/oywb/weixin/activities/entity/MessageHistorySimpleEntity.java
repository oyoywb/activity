package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.sql.Timestamp;

@Data
@SqlResultSetMapping(name = "MessageHistorySimpleEntity",
        entities = @EntityResult(entityClass = MessageHistorySimpleEntity.class,
                fields = {
                        @FieldResult(name = "otherUserId", column = "other_user_id"),
                        @FieldResult(name = "lastChatTime", column = "last_chat_time"),
                        @FieldResult(name = "context", column = "context"),
                        @FieldResult(name = "name", column = "name"),
                        @FieldResult(name = "profile", column = "profile")
                }
        )
)
public class MessageHistorySimpleEntity {
    private long otherUserId;
    private Timestamp lastChatTime;
    private String context;
    private String name;
    private String profile;
}
