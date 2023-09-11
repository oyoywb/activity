package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.DynamicsCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DynamicsCommentRepository extends JpaRepository<DynamicsCommentEntity, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "delete from dynamics where id = ?2 and user_id =?1")
    void deleteDynamics(long userId, long id);

    @Query(nativeQuery = true, value = "select * from dynamics_comment where id = ?1 and user_id =?2")
    DynamicsCommentEntity getDynamicsCommentEntitiesByIdAndUserId(long id, long userId);

    @Query(nativeQuery = true, value = "select dc.* from dynamics_comment dc, dynamics d where dc.dy_id = d.id and d.user_id = ?1")
    List<DynamicsCommentEntity> getDynamicsCommentEntitiesByUserId(long userId);

    @Query(nativeQuery = true, value = "select * from dynamics_comment WHERE user_id = ?1")
    List<DynamicsCommentEntity> getDynamicsCommentEntitiesMySelf(long userId);
    @Modifying
    @Query(nativeQuery = true, value = "delete from dynamics_comment where dy_id = ?1")
    void deleteDynamicsComment(long id);

    @Modifying
    @Query(nativeQuery = true, value = "delete from dynamics_comment where dy_id in (?1)")
    void batchDeleteDynamicsComment(List<Long> ids);
}
