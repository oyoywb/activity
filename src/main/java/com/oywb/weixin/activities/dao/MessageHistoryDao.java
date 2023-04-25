package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.MessageHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageHistoryDao extends JpaRepository<MessageHistoryEntity, Long> {

}
