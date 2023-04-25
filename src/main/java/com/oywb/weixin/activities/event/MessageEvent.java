package com.oywb.weixin.activities.event;

import com.oywb.weixin.activities.entity.MessageHistoryEntity;
import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {
    private MessageHistoryEntity messageHistoryEntity;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MessageEvent(Object source, MessageHistoryEntity messageHistoryEntity) {
        super(source);
        this.messageHistoryEntity = messageHistoryEntity;
    }

    public MessageHistoryEntity getMessageHistoryEntity() {
        return messageHistoryEntity;
    }

    public void setMessageHistoryEntity(MessageHistoryEntity messageHistoryEntity) {
        this.messageHistoryEntity = messageHistoryEntity;
    }
}
