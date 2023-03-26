package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.RoleBinding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleBindingRepository extends JpaRepository<RoleBinding, String> {
    public RoleBinding findByOpenidAndUserRole(@Param("openid") String openid, @Param("user_role") String userRole);
}
