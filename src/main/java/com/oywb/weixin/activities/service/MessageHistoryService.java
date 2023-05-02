package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.entity.MessageHistoryEntity;
import com.oywb.weixin.activities.entity.MessageHistorySimpleEntity;

import java.util.List;

public interface MessageHistoryService {
    List<MessageHistorySimpleEntity> getMessageSimple(String openId);

    List<MessageHistoryEntity> getMessageDetail(String openId, long toUser);
}
