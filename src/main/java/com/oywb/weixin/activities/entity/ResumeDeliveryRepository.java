package com.oywb.weixin.activities.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResumeDeliveryRepository extends JpaRepository<ResumeDeliveryEntity, Long> {
    @Query(countQuery = "select count(*) from resume_delivery where project_id = ?1 and pass = 1", nativeQuery = true)
    int getCountByProjectId(long projectId);

    @Query(value = "delete from resume_delivery ", nativeQuery = true)
    void deleteByUserIdAndProjectId(long userId, long projectId);

    @Query(value = "update resume_delivery set pass = 1 where project_id = ?1 and user_id in (?2)", nativeQuery = true)
    void updateByProjectIdAndUserId(long projectId, List<Long> userIds);
}
