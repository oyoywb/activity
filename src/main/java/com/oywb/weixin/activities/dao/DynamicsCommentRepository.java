package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.DynamicsCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DynamicsCommentRepository extends JpaRepository<DynamicsCommentEntity, Long> {
}
