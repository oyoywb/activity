package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InformationRepository extends JpaRepository<InformationEntity, Long> {

    @Query(value = "select * from information where activity_id =?1", nativeQuery = true)
    InformationEntity findByActivityId(long acId);
}
