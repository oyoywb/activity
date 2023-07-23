package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.dao.MessageHistoryRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.entity.MessageHistoryEntity;
import com.oywb.weixin.activities.entity.MessageHistorySimpleEntity;
import com.oywb.weixin.activities.service.MessageHistoryService;
import com.oywb.weixin.activities.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class MessageHistoryServiceImpl implements MessageHistoryService {

    private final EntityManager entityManager;
    private final UserService userService;
    private final MessageHistoryRepository messageHistoryRepository;

    public MessageHistoryServiceImpl(EntityManager entityManager, UserService userService, MessageHistoryRepository messageHistoryRepository) {
        this.entityManager = entityManager;
        this.userService = userService;
        this.messageHistoryRepository = messageHistoryRepository;
    }

    @Override
    public List<MessageHistorySimpleEntity> getMessageSimple(String openId) {
        long userId = userService.getUserId(openId);
        String sql = "SELECT DISTINCT" +
                "    CASE" +
                "        WHEN sender = :userId THEN receiver" +
                "        ELSE sender" +
                "    END AS other_user_id," +
                "    MAX(ts) AS last_chat_time," +
                "    context" +
                "    u.profile" +
                "    u.name" +
                " FROM message_history" +
                " LEFT JOIN user u ON " +
                "    u.id = CASE " +
                "               WHEN mh.sender = :userId THEN mh.receiver " +
                "               ELSE mh.sender " +
                "           END" +
                " WHERE sender = :userId OR receiver = :userId" +
                " GROUP BY other_user_id" +
                " ORDER BY last_chat_time DESC";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId", userId);

        List<MessageHistorySimpleEntity> messageHistorySimpleEntityList = query.getResultList();

        return messageHistorySimpleEntityList;
    }

    @Override
    public List<MessageHistoryEntity> getMessageDetail(String openId, long toUser) {
        long userId = userService.getUserId(openId);
        List<MessageHistoryEntity> messageHistoryEntities = messageHistoryRepository.getMessageHistoryEntity(userId, toUser);

        return messageHistoryEntities;
    }
}
