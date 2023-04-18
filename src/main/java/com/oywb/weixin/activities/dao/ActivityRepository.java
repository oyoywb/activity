package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

    @Query(value = "select * from activity where school = ?1 and campus = ?2 and start between ?3 and ?4", nativeQuery = true)
    List<ActivityEntity> findActivityEntitiesByStartBetween(String school, String campus, Timestamp start, Timestamp end);

    @Query(value = "select * from activity where user_id=?1 and verified=?2", nativeQuery = true)
    List<ActivityEntity> getSelfActivity(long userId, byte flag);
    @Query(value = "select a.* from activity a,information_detail ifmd where a.id = ifmd.activity_id and ifmd.user_id = ?1", nativeQuery = true)
    List<ActivityEntity> getSelfSignActivity(long userId);
}
