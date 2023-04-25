package com.oywb.weixin.activities.event;

import com.oywb.weixin.activities.dao.MessageHistoryDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Component
@Slf4j
@Transactional
public class MessageEventListener implements ApplicationListener<MessageEvent> {

    private final MessageHistoryDao messageHistoryDao;

    public MessageEventListener(MessageHistoryDao messageHistoryDao) {
        this.messageHistoryDao = messageHistoryDao;
    }

    @Override
    public void onApplicationEvent(MessageEvent event) {
        log.debug("save message into db");
        messageHistoryDao.saveAndFlush(event.getMessageHistoryEntity());
    }
}
