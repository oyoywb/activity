package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.PersonalPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends JpaRepository<PersonalPlanEntity, Long> {

    @Query(value = "select * from personal_plan where user_id = :userId", nativeQuery = true)
    public List<PersonalPlanEntity> findPersonalPlanEntitiesByUserId(@Param("userId") long userId);

    //@Query(value = "select id from personal_plan where id = :id and user_id = :userId", nativeQuery = true)
    public boolean existsByIdAndUserId(long id, long userId);

    public PersonalPlanEntity findPersonalPlanEntityByIdAndUserId(long id, long userId);
}
