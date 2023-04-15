package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<LikesEntity, Long> {
    @Query(value = "select * from likes where user_id = ?1 and dy_id = ?2", nativeQuery = true)
    LikesEntity getByUserIdAndProjectId(long userId, long dyId);
}
