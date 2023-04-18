package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    @Query(value = "select * from project where user_id = ?1 and pass = ?2 ", nativeQuery = true)
    List<ProjectEntity> getSelfProject(long userId, byte passed);

    @Query(value = "select p.* from project p,resume_delivery rd WHERE p.id = rd.project_id and rd.user_id = ?1", nativeQuery = true)
    List<ProjectEntity> getSelfSignProject(long userId);
}
