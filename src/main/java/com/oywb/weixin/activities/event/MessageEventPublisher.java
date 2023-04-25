package com.oywb.weixin.activities.event;

import com.oywb.weixin.activities.entity.MessageHistoryEntity;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MessageEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public MessageEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishMessageEvent(final MessageHistoryEntity messageHistoryEntity) {
        MessageEvent messageEvent = new MessageEvent(this, messageHistoryEntity);
        applicationEventPublisher.publishEvent(messageEvent);
    }
}
