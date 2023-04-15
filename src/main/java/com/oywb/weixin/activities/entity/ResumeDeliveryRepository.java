package com.oywb.weixin.activities.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResumeDeliveryRepository extends JpaRepository<ResumeDeliveryEntity, Long> {
    @Query(countQuery = "select count(*) from resume_delivery where project_id = ?1 and pass = 1", nativeQuery = true)
    int getCountByProjectId(long projectId);
}
