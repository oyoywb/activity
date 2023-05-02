package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.MessageHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageHistoryRepository extends JpaRepository<MessageHistoryEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM message_history WHERE (sender = ?1 AND receiver = ?2) OR (sender = ?2 AND receiver = ?1)")
    List<MessageHistoryEntity> getMessageHistoryEntity(long user1, long user2);
}
