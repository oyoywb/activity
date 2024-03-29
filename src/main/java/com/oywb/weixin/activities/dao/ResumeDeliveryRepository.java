package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.ResumeDeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResumeDeliveryRepository extends JpaRepository<ResumeDeliveryEntity, Long> {
    @Query(countQuery = "select count(*) from resume_delivery where project_id = ?1 and pass = 1", nativeQuery = true)
    Integer countByProjectId(long projectId);

    @Modifying
    @Query(value = "delete from resume_delivery ", nativeQuery = true)
    void deleteByUserIdAndProjectId(long userId, long projectId);

    @Modifying
    @Query(value = "update resume_delivery set pass = ?3 where project_id = ?1 and user_id in (?2)", nativeQuery = true)
    void updateByProjectIdAndUserId(long projectId, List<Long> userIds, byte flag);
}
