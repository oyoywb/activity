package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByOpenid(@Param("openid") String openid);


    @Modifying
    @Query(value = "update user set registed = :pass where id in :userIds", nativeQuery = true)
    List<UserEntity> authByAdmin(@Param("userIds") List<String> userIds,@Param("pass") byte pass);

    @Query(value = "select id from user where openid = ?1", nativeQuery = true)
    long getUserIdByOpenId(String openId);

    @Query(value = "select * from user where registed = ?1", nativeQuery = true)
    Page<UserEntity> getAllByRegisted(byte registed, Pageable pageable);
}
