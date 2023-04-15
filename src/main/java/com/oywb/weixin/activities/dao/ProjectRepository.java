package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

}
