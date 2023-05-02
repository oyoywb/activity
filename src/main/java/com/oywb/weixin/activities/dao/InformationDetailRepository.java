package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.InformationDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InformationDetailRepository extends JpaRepository<InformationDetailEntity, Long> {
    @Query(countQuery = "select count(*) from information_detail where activity_id =?1 and passed=1", nativeQuery = true)
    Long countPassedByActivityId(long activityId);

    @Modifying
    @Query(value = "delete from information_detail where activity_id = ?1 and user_id =?2", nativeQuery = true)
    void deleteByUserIdActivityId(long activityId, long openId);

    @Query(value = "select * from information_detail where activity_id =?1 and passed = ?2", nativeQuery = true)
    List<InformationDetailEntity> getInformationDetailEntitiesByActivityIdAndPassed(long activityId, byte passed);

    @Modifying
    @Query(value = "update information_detail set passed=1 where id in (?1)", nativeQuery = true)
    void pass(List<Long> ids);
}
