package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByOpenid(@Param("openid") String openid);


    @Modifying
    @Query(value = "update user set registed = 1 where id in :userIds", nativeQuery = true)
    List<UserEntity> authByAdmin(@Param("userIds") List<String> userIds);

    @Query(value = "select id from user where open_id = ?1", nativeQuery = true)
    long getUserIdByOpenId(String openId);

}
