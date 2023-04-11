package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.InformationDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InformationDetailRepository extends JpaRepository<InformationDetailEntity, Long> {
    @Query(countQuery = "select count(*) from information_detail where activity_id =?1 and passed=1", nativeQuery = true)
    long countPassedByActivityId(long activityId);

}
