package com.oywb.weixin.activities.event;

import com.oywb.weixin.activities.dao.MessageHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Slf4j
@Transactional
public class MessageEventListener implements ApplicationListener<MessageEvent> {

    private final MessageHistoryRepository messageHistoryDao;

    public MessageEventListener(MessageHistoryRepository messageHistoryDao) {
        this.messageHistoryDao = messageHistoryDao;
    }

    @Override
    public void onApplicationEvent(MessageEvent event) {
        log.debug("save message into db");
        messageHistoryDao.saveAndFlush(event.getMessageHistoryEntity());
    }
}
