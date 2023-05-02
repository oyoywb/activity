package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.DynamicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DynamicsRepository extends JpaRepository<DynamicsEntity, Long> {

    @Query(nativeQuery = true, value = "select * from dynamics where id = ?1 and user_id = ?2")
    DynamicsEntity findByIdAndUserId(long id, long userId);

    @Modifying
    @Query(nativeQuery = true, value = "delete from dynamics where id = ?2 and user_id =?1")
    void deleteDynamics(long userId, long id);

    @Modifying
    @Query(nativeQuery = true, value = "delete from likes where dy_id = ?1")
    void deleteDynamicsLikes(long id);
}
